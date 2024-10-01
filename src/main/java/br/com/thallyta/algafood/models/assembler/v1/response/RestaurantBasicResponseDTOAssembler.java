package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.RestaurantController;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantBasicResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantBasicResponseDTOAssembler
        extends RepresentationModelAssemblerSupport<Restaurant, RestaurantBasicResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestaurantBasicResponseDTOAssembler() {
        super(RestaurantController.class, RestaurantBasicResponseDTO.class);
    }

    @Override
    public @NotNull RestaurantBasicResponseDTO toModel(@NotNull Restaurant restaurant) {
        RestaurantBasicResponseDTO restaurantDTO = createModelWithId(
                restaurant.getId(), restaurant);

        modelMapper.map(restaurant, restaurantDTO);

        restaurantDTO.add(algaLinks.linkToRestaurants("restaurants"));

        restaurantDTO.getKitchen().add(
                algaLinks.linkToKitchen(restaurant.getKitchen().getId()));

        return restaurantDTO;
    }

    @Override
    public @NotNull CollectionModel<RestaurantBasicResponseDTO> toCollectionModel(@NotNull Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurants());
    }
}
