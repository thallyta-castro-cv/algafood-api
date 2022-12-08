package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.model.State;

import java.util.List;

public interface StateRepository {

    List<State> getAll();
    State getById(Long id);
    State save(State state);
    void delete(State state);
}
