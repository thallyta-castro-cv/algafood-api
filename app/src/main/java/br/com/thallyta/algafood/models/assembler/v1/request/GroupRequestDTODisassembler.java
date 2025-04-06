package br.com.thallyta.algafood.models.assembler.v1.request;

import br.com.thallyta.algafood.models.Group;
import br.com.thallyta.algafood.models.dtos.v1.requests.GroupRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Group toDomainObject(GroupRequestDTO groupRequestDTO) {
        return modelMapper.map(groupRequestDTO, Group.class);
    }

    public void copyToDomainObject(GroupRequestDTO groupRequestDTO, Group group) {
        modelMapper.map(groupRequestDTO, group);
    }
}
