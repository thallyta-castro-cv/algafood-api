package br.com.thallyta.algafood.repositories.queries.restaurant;

import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.params.ListRestaurantParams;

import java.util.List;

public interface RestaurantQueries {

    List<Restaurant> list(ListRestaurantParams params);
}
