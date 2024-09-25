package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.controllers.openapi.PermissionControllerOpenApi;
import br.com.thallyta.algafood.models.Permission;
import br.com.thallyta.algafood.models.assembler.response.PermissionResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.responses.PermissionResponseDTO;
import br.com.thallyta.algafood.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/permissions")
public class PermissionController implements PermissionControllerOpenApi {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionResponseDTOAssembler permissionAssembler;

    @GetMapping
    public CollectionModel<PermissionResponseDTO> getAll() {
        List<Permission> allPermissions = permissionRepository.findAll();
        return permissionAssembler.toCollectionModel(allPermissions);
    }
}
