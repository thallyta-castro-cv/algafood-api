package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.models.dtos.responses.StateResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public StateResponseDTO toStateResponse(State state) {
        return modelMapper.map(state, StateResponseDTO.class);
    }

    public List<StateResponseDTO> toCollectionModel(List<State> states) {
        return states.stream()
                .map(this::toStateResponse)
                .collect(Collectors.toList());
    }
}
