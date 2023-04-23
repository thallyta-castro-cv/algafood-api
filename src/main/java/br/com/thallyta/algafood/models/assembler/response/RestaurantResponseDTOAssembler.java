package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.dtos.responses.RestaurantResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantResponseDTO toRestaurantResponse(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantResponseDTO.class);
    }

    public List<RestaurantResponseDTO> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toRestaurantResponse)
                .collect(Collectors.toList());
    }
}
