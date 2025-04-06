package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.openapi.UserControllerOpenApi;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.UserSystem;
import br.com.thallyta.algafood.models.assembler.v1.request.UserRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.v1.response.UserResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.requests.UserPasswordRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.requests.UserRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.requests.UserWithPasswordRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.UserResponseDTO;
import br.com.thallyta.algafood.repositories.UserRepository;
import br.com.thallyta.algafood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController implements UserControllerOpenApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserResponseDTOAssembler userResponseAssembler;

    @Autowired
    private UserRequestDTODisassembler userRequestDisassembler;

    @GetMapping
    @CheckSecurity.UserGroupsPermissions.CanGet
    public CollectionModel<UserResponseDTO> getAll() {
        List<UserSystem> allUsers = userRepository.findAll();
        return userResponseAssembler.toCollectionModel(allUsers);
    }

    @GetMapping("/{userId}")
    @CheckSecurity.UserGroupsPermissions.CanGet
    public UserResponseDTO getUser(@PathVariable Long userId) {
        UserSystem user = userService.findOrFail(userId);
        return userResponseAssembler.toModel(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.UserGroupsPermissions.CanEdit
    public UserResponseDTO create(@RequestBody @Valid UserWithPasswordRequestDTO userId) {
        UserSystem user = userRequestDisassembler.toDomainObject(userId);
        user = userService.save(user);
        return userResponseAssembler.toModel(user);
    }

    @PutMapping("/{userId}")
    @CheckSecurity.UserGroupsPermissions.CanChangeUser
    public UserResponseDTO update(@PathVariable Long userId,
                                  @RequestBody @Valid UserRequestDTO userInput) {
        UserSystem currentUser = userService.findOrFail(userId);
        userRequestDisassembler.copyToDomainObject(userInput, currentUser);
        currentUser = userService.save(currentUser);
        return userResponseAssembler.toModel(currentUser);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.UserGroupsPermissions.CanChangeOwnPassword
    public ResponseEntity<Void> updatePassword(@PathVariable Long userId, @RequestBody @Valid UserPasswordRequestDTO password) {
        userService.updatePassword(userId, password.getCurrentPassword(), password.getNewPassword());
        return ResponseEntity.noContent().build();
    }
}
