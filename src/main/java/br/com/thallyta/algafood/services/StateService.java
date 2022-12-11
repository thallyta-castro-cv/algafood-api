package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.common.exceptions.NotFound;
import br.com.thallyta.algafood.model.State;
import br.com.thallyta.algafood.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public List<State> getAll() {
        return stateRepository.findAll();
    }

    public void delete(Long id){
        try{
            stateRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFound("Cozinha não encontrada!");
        }
    }


}
