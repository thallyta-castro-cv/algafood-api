package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.Permission;
import br.com.thallyta.algafood.models.assembler.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.responses.PermissionResponseDTO;
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

    @Override
    public @NotNull PermissionResponseDTO toModel(@NotNull Permission permission) {
       return modelMapper.map(permission, PermissionResponseDTO.class);
    }

    @Override
    public @NotNull CollectionModel<PermissionResponseDTO> toCollectionModel(Iterable<? extends Permission> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(algaLinks.linkToPermissions());
    }
}
