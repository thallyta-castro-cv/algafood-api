package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.UserController;
import br.com.thallyta.algafood.models.UserSystem;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.UserResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDTOAssembler
        extends RepresentationModelAssemblerSupport<UserSystem, UserResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AccessService accessService;

    public UserResponseDTOAssembler() {
        super(UserController.class, UserResponseDTO.class);
    }

    @Override
    public @NotNull UserResponseDTO toModel(@NotNull UserSystem user) {
        UserResponseDTO userResponseDTO = createModelWithId(user.getId(), user);
        modelMapper.map(user, userResponseDTO);

        if (accessService.canGetUserGroupPermission()){
            userResponseDTO.add(algaLinks.linkToUsers("users"));
            userResponseDTO.add(algaLinks.linkToUserGroups(user.getId(), "user-groups"));
        }

        return userResponseDTO;
    }

    @Override
    public @NotNull CollectionModel<UserResponseDTO> toCollectionModel(@NotNull Iterable<? extends UserSystem> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToUsers());
    }


}
