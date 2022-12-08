package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> getAll();
    Restaurant getById(Long id);
    Restaurant save(Restaurant restaurant);
    void delete(Restaurant restaurant);
}
