package br.com.thallyta.algafood.repositories.queries.restaurant;

import br.com.thallyta.algafood.model.Restaurant;
import br.com.thallyta.algafood.model.params.ListRestaurantParams;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantQueries {

    List<Restaurant> list(ListRestaurantParams params);
}
