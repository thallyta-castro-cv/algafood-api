package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.StateController;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.StateResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class StateResponseDTOAssembler
        extends RepresentationModelAssemblerSupport<State, StateResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AccessService accessService;

    public StateResponseDTOAssembler() {
        super(StateController.class, StateResponseDTO.class);
    }

    @Override
    public @NotNull StateResponseDTO toModel(@NotNull State state) {
        StateResponseDTO stateDTO = createModelWithId(state.getId(), state);
        modelMapper.map(state, stateDTO);

        if (accessService.canGetStates()){
            stateDTO.add(algaLinks.linkToStates("states"));
        }

        return stateDTO;
    }

    @Override
    public @NotNull CollectionModel<StateResponseDTO> toCollectionModel(@NotNull Iterable<? extends State> states) {
        CollectionModel<StateResponseDTO> collectionModel = super.toCollectionModel(states);

        if (accessService.canGetStates()) {
            collectionModel.add(algaLinks.linkToStates());
        }

        return collectionModel;
    }
}
