package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.Group;
import br.com.thallyta.algafood.models.dtos.responses.GroupResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GroupResponseDTO toGroupResponse(Group group) {
        return modelMapper.map(group, GroupResponseDTO.class);
    }

    public List<GroupResponseDTO> toCollectionModel(Collection<Group> groups) {
        return groups.stream()
                .map(this::toGroupResponse)
                .collect(Collectors.toList());
    }
}
