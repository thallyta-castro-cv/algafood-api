package br.com.thallyta.algafood.models.assembler.v1.request;

import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.dtos.v1.requests.RestaurantRequestDTO;
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
        if(restaurant.getAddress() != null ){
            restaurant.getAddress().setCity(new City());
        }
        modelMapper.map(restaurantRequestDTO, restaurant);
    }
}
