package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.v1.openapi.PermissionGroupControllerOpenApi;
import br.com.thallyta.algafood.models.Group;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.assembler.v1.response.PermissionResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.responses.PermissionResponseDTO;
import br.com.thallyta.algafood.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/groups/{groupId}/permissions")
public class PermissionGroupController implements PermissionGroupControllerOpenApi {

    @Autowired
    private GroupService groupService;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private PermissionResponseDTOAssembler permissionResponseDTOAssembler;

    @GetMapping
    public CollectionModel<PermissionResponseDTO> getAll(@PathVariable Long groupId) {
        Group group = groupService.findOrFail(groupId);

        CollectionModel<PermissionResponseDTO> permissionDTO =
                permissionResponseDTOAssembler.toCollectionModel(group.getPermissions())
                .removeLinks()
                .add(algaLinks.linkToGroupPermissions(groupId))
                .add(algaLinks.linkToGroupPermissionsBind(groupId, "bind"));

        permissionDTO.getContent().forEach(permission -> {
            permission.add(algaLinks.linkToGroupPermissionsUnbind(
                    groupId, permission.getId(), "unbind"));
        });

        return permissionDTO;
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> unbind(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.unbindPermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> bind(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.bindPermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }
}
