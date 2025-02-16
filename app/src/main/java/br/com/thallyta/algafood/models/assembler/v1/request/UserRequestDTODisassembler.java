package br.com.thallyta.algafood.models.assembler.v1.request;

import br.com.thallyta.algafood.models.UserSystem;
import br.com.thallyta.algafood.models.dtos.v1.requests.UserRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public UserSystem toDomainObject(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, UserSystem.class);
    }

    public void copyToDomainObject(UserRequestDTO userRequestDTO, UserSystem user) {
        modelMapper.map(userRequestDTO, user);
    }
}
