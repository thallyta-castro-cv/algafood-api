package br.com.thallyta.algafood.models.assembler.request;

import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.dtos.requests.RestaurantRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class RestaurantRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantRequestDTO restaurantRequestDTO) {
        return modelMapper.map(restaurantRequestDTO, Restaurant.class);
    }

    public void copyToDomainObject(@Valid RestaurantRequestDTO restaurantRequestDTO, Restaurant restaurant) {
        restaurant.setKitchen(new Kitchen());
        modelMapper.map(restaurantRequestDTO, restaurant);
    }
}
