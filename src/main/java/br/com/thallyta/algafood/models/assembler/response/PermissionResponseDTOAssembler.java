package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.Permission;
import br.com.thallyta.algafood.models.dtos.responses.PermissionResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissionResponseDTO toModel(Permission permission) {
        return modelMapper.map(permission, PermissionResponseDTO.class);
    }

    public List<PermissionResponseDTO> toCollectionModel(Collection<Permission> permissions) {
        return permissions.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
