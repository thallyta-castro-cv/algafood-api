package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.core.exceptions.EntityExceptionInUse;
import br.com.thallyta.algafood.models.Kitchen;
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
            throw new EntityExceptionInUse("Cozinha não pode ser removida, pois está em uso.");
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Cozinha não encontrada!");
        }
    }

    public Kitchen findOrFail(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cozinha não encontrada!"));
    }
}