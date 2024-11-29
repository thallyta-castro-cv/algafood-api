package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.v1.openapi.UserGroupControllerOpenApi;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.assembler.v1.response.GroupResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.responses.GroupResponseDTO;
import br.com.thallyta.algafood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/users/{userId}/groups")
public class UserGroupController implements UserGroupControllerOpenApi {

    @Autowired
    private UserService userService;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private GroupResponseDTOAssembler groupResponseDTOAssembler;

    @GetMapping
    @CheckSecurity.UserGroupsPermissions.CanGet
    public CollectionModel<GroupResponseDTO> getAll(@PathVariable Long userId) {
        User user = userService.findOrFail(userId);

        CollectionModel<GroupResponseDTO> groupDTO = groupResponseDTOAssembler.toCollectionModel(user.getGroups())
                .removeLinks()
                .add(algaLinks.linkToUserGroupBind(userId, "bind"));

        groupDTO.getContent().forEach(group -> group.add(algaLinks.linkToUserGroupUnbind(
                userId, group.getId(), "unbind")));

        return groupDTO;
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.UserGroupsPermissions.CanEdit
    public ResponseEntity<Void> unbind(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.unbindGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.UserGroupsPermissions.CanEdit
    public ResponseEntity<Void> bind(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.bindGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }
}
