package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.openapi.UserGroupControllerOpenApi;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.assembler.response.GroupResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.responses.GroupResponseDTO;
import br.com.thallyta.algafood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users/{userId}/groups")
public class UserGroupController implements UserGroupControllerOpenApi {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupResponseDTOAssembler groupResponseDTOAssembler;

    @GetMapping
    public List<GroupResponseDTO> getAll(@PathVariable Long userId) {
        User user = userService.findOrFail(userId);
        return groupResponseDTOAssembler.toCollectionModel(user.getGroups());
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbind(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.unbindGroup(userId, groupId);
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bind(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.bindGroup(userId, groupId);
    }
}
