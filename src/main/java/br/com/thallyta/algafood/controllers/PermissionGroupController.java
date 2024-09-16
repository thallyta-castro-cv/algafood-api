package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.controllers.openapi.PermissionGroupControllerOpenApi;
import br.com.thallyta.algafood.models.Group;
import br.com.thallyta.algafood.models.assembler.response.PermissionResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.responses.PermissionResponseDTO;
import br.com.thallyta.algafood.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups/{groupId}/permissions")
public class PermissionGroupController implements PermissionGroupControllerOpenApi {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionResponseDTOAssembler permissionResponseDTOAssembler;

    @GetMapping
    public List<PermissionResponseDTO> getAll(@PathVariable Long groupId) {
        Group group = groupService.findOrFail(groupId);
        return permissionResponseDTOAssembler.toCollectionModel(group.getPermissions());
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbind(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.unbindPermission(groupId, permissionId);
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bind(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.bindPermission(groupId, permissionId);
    }
}
