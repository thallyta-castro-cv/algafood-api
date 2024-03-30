package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.openapi.UserControllerOpenApi;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.assembler.request.UserRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.UserResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.UserPasswordRequestDTO;
import br.com.thallyta.algafood.models.dtos.requests.UserRequestDTO;
import br.com.thallyta.algafood.models.dtos.requests.UserWithPasswordRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.UserResponseDTO;
import br.com.thallyta.algafood.repositories.UserRepository;
import br.com.thallyta.algafood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
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
    public List<UserResponseDTO> getAll() {
        List<User> allUsers = userRepository.findAll();
        return userResponseAssembler.toCollectionModel(allUsers);
    }

    @GetMapping("/{userId}")
    public UserResponseDTO getUser(@PathVariable Long userId) {
        User user = userService.findOrFail(userId);
        return userResponseAssembler.toModel(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO create(@RequestBody @Valid UserWithPasswordRequestDTO userId) {
        User user = userRequestDisassembler.toDomainObject(userId);
        user = userService.save(user);
        return userResponseAssembler.toModel(user);
    }

    @PutMapping("/{userId}")
    public UserResponseDTO update(@PathVariable Long userId,
                                  @RequestBody @Valid UserRequestDTO userInput) {
        User currentUser = userService.findOrFail(userId);
        userRequestDisassembler.copyToDomainObject(userInput, currentUser);
        currentUser = userService.save(currentUser);

        return userResponseAssembler.toModel(currentUser);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long userId, @RequestBody @Valid UserPasswordRequestDTO password) {
        userService.updatePassword(userId, password.getCurrentPassword(), password.getNewPassword());
    }
}
