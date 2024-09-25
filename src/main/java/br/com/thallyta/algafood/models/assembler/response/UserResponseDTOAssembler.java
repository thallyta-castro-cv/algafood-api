package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.controllers.UserController;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.assembler.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.responses.UserResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDTOAssembler
        extends RepresentationModelAssemblerSupport<User, UserResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public UserResponseDTOAssembler() {
        super(UserController.class, UserResponseDTO.class);
    }

    @Override
    public @NotNull UserResponseDTO toModel(@NotNull User user) {
        UserResponseDTO userResponseDTO = createModelWithId(user.getId(), user);
        modelMapper.map(user, userResponseDTO);
        userResponseDTO.add(algaLinks.linkToUsers("users"));
        userResponseDTO.add(algaLinks.linkToUserGroups(user.getId(), "user-groups"));
        return userResponseDTO;
    }

    @Override
    public @NotNull CollectionModel<UserResponseDTO> toCollectionModel(@NotNull Iterable<? extends User> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToUsers());
    }


}
