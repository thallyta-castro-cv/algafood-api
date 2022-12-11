package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.common.exceptions.NotFound;
import br.com.thallyta.algafood.common.exceptions.ValidateMessageException;
import br.com.thallyta.algafood.model.Kitchen;
import br.com.thallyta.algafood.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public List<Kitchen> getAll() {
        return kitchenRepository.findAll();
    }

    public void delete(Long id){
        try{
          kitchenRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new ValidateMessageException("Cozinha não pode ser removida, pois está em uso.");
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFound("Cozinha não encontrada!");
        }
    }
}