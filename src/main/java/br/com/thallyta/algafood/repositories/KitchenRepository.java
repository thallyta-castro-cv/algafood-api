package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> getAll();
    Kitchen getById(Long id);
    Kitchen save(Kitchen kitchen);
    void delete(Kitchen kitchen);

}
