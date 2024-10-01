package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.GroupController;
import br.com.thallyta.algafood.models.Group;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.GroupResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GroupResponseDTOAssembler extends
        RepresentationModelAssemblerSupport<Group, GroupResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public GroupResponseDTOAssembler() {
        super(GroupController.class, GroupResponseDTO.class);
    }

    @Override
    public @NotNull GroupResponseDTO toModel(@NotNull Group group) {
        GroupResponseDTO groupDTO = createModelWithId(group.getId(), group);
        modelMapper.map(group, groupDTO);

        groupDTO.add(algaLinks.linkToGroups("groups"));

        groupDTO.add(algaLinks.linkToGroupPermissions(group.getId(), "permissions"));

        return groupDTO;
    }

    @Override
    public @NotNull CollectionModel<GroupResponseDTO> toCollectionModel(@NotNull Iterable<? extends Group> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToGroups());
    }
}
