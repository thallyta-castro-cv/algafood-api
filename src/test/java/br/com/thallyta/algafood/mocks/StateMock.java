package br.com.thallyta.algafood.mocks;

import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.models.dtos.requests.StateIdRequestDTO;

public class StateMock {

    public static final String STATE_NOT_FOUND = "Estado não existe!";

    public State stateAdd() {
        State state = new State();
        state.setId(1L);
        state.setName("São Paulo");
        return state;
    }

    public StateIdRequestDTO stateAddRequestDTO() {
        StateIdRequestDTO state = new StateIdRequestDTO();
        state.setId(1L);
        return state;
    }
}
