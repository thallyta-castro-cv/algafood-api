package br.com.thallyta.algafood.models.mixin;

import br.com.thallyta.algafood.models.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class KitchenMixin {

    @JsonIgnore
    private List<Restaurant> restaurants;
}
