package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.EntityExceptionInUse;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public void delete(Long id){
        try{
          kitchenRepository.deleteById(id);
          kitchenRepository.flush();
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

    public Page<Kitchen> getAll(Pageable pageable) {
        return kitchenRepository.findAll(pageable);
    }
}