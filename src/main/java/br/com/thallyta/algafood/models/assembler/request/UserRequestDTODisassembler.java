package br.com.thallyta.algafood.models.assembler.request;

import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.dtos.requests.UserRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public User toDomainObject(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, User.class);
    }

    public void copyToDomainObject(UserRequestDTO userRequestDTO, User user) {
        modelMapper.map(userRequestDTO, user);
    }
}
