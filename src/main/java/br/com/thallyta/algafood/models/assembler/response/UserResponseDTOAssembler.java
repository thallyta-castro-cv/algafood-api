package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.controllers.UserController;
import br.com.thallyta.algafood.controllers.UserGroupController;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.dtos.responses.UserResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserResponseDTOAssembler
        extends RepresentationModelAssemblerSupport<User, UserResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDTOAssembler() {
        super(UserController.class, UserResponseDTO.class);
    }

    @Override
    public @NotNull UserResponseDTO toModel(@NotNull User user) {
        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        userResponseDTO.add(linkTo(UserController.class)
                .slash(userResponseDTO.getId()).withSelfRel());
        userResponseDTO.add(linkTo(UserController.class).withRel("users"));
        userResponseDTO.add(linkTo(methodOn(UserGroupController.class).getAll(user.getId())).withRel("users-groups"));
        return userResponseDTO;
    }

    @Override
    public @NotNull CollectionModel<UserResponseDTO> toCollectionModel(@NotNull Iterable<? extends User> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(UserController.class).withSelfRel());
    }


}
