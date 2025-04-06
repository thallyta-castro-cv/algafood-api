package br.com.thallyta.algafood.core.jackson;

import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.mixin.CityMixin;
import br.com.thallyta.algafood.models.mixin.KitchenMixin;
import br.com.thallyta.algafood.models.mixin.RestaurantMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
        setMixInAnnotation(City.class, CityMixin.class);
        setMixInAnnotation(Kitchen.class, KitchenMixin.class);
    }
}
