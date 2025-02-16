package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.models.Permission;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.PermissionResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissionResponseDTOAssembler implements
        RepresentationModelAssembler<Permission, PermissionResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AccessService accessService;

    @Override
    public @NotNull PermissionResponseDTO toModel(@NotNull Permission permission) {
       return modelMapper.map(permission, PermissionResponseDTO.class);
    }

    @Override
    public @NotNull CollectionModel<PermissionResponseDTO> toCollectionModel(Iterable<? extends Permission> entities) {
        CollectionModel<PermissionResponseDTO> collectionModel
                = RepresentationModelAssembler.super.toCollectionModel(entities);

        if (accessService.canGetUserGroupPermission()) {
            collectionModel.add(algaLinks.linkToPermissions());
        }

        return collectionModel;
    }
}
