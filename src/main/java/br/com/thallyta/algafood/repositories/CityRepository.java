package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.model.City;

import java.util.List;

public interface CityRepository {

    List<City> getAll();
    City getById(Long id);
    City save(City city);
    void delete(City city);
}
