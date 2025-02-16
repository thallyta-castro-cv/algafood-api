package br.com.thallyta.algafood.models.mixin;

import br.com.thallyta.algafood.models.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CityMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;
}
