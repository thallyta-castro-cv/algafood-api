package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.common.exceptions.NotFoundException;
import br.com.thallyta.algafood.common.exceptions.EntityExceptionInUse;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            throw new NotFoundException("Estado não encontrado!");
        } catch (DataIntegrityViolationException exception) {
            throw new EntityExceptionInUse("Estado não pode ser removido, pois está em uso.");
        }
    }

    public State findOrFail(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado não encontrado!"));
    }


}
