package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.dtos.responses.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDTO toModel(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public List<UserResponseDTO> toCollectionModel(List<User> users) {
        return users.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
