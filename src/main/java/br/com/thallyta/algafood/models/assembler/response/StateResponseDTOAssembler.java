package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.controllers.StateController;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.models.assembler.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.responses.StateResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class StateResponseDTOAssembler
        extends RepresentationModelAssemblerSupport<State, StateResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public StateResponseDTOAssembler() {
        super(StateController.class, StateResponseDTO.class);
    }

    @Override
    public @NotNull StateResponseDTO toModel(@NotNull State state) {
        StateResponseDTO stateDTO = createModelWithId(state.getId(), state);
        modelMapper.map(state, stateDTO);
        stateDTO.add(algaLinks.linkToStates("states"));
        return stateDTO;
    }

    @Override
    public @NotNull CollectionModel<StateResponseDTO> toCollectionModel(@NotNull Iterable<? extends State> states) {
        return super.toCollectionModel(states)
                .add(linkTo(StateController.class).withSelfRel());
    }
}
