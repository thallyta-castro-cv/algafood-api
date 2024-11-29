package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.v1.openapi.PermissionControllerOpenApi;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.Permission;
import br.com.thallyta.algafood.models.assembler.v1.response.PermissionResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.responses.PermissionResponseDTO;
import br.com.thallyta.algafood.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/permissions")
public class PermissionController implements PermissionControllerOpenApi {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionResponseDTOAssembler permissionAssembler;

    @GetMapping
    @CheckSecurity.UserGroupsPermissions.CanGet
    public CollectionModel<PermissionResponseDTO> getAll() {
        List<Permission> allPermissions = permissionRepository.findAll();
        return permissionAssembler.toCollectionModel(allPermissions);
    }
}
