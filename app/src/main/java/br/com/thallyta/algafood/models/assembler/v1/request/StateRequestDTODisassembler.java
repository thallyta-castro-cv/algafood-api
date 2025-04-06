package br.com.thallyta.algafood.models.assembler.v1.request;

import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.models.dtos.v1.requests.StateRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public State toDomainObject(StateRequestDTO stateRequestDTO) {
        return modelMapper.map(stateRequestDTO, State.class);
    }

    public void copyToDomainObject(StateRequestDTO stateRequestDTO, State state) {
        modelMapper.map(stateRequestDTO, state);
    }
}
