package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.RestaurantController;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantOnlyNameResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantOnlyNameDTOAssembler
        extends RepresentationModelAssemblerSupport<Restaurant, RestaurantOnlyNameResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestaurantOnlyNameDTOAssembler() {
        super(RestaurantController.class, RestaurantOnlyNameResponseDTO.class);
    }

    @Override
    public @NotNull RestaurantOnlyNameResponseDTO toModel(@NotNull Restaurant restaurant) {
        RestaurantOnlyNameResponseDTO restaurantDTO = createModelWithId(
                restaurant.getId(), restaurant);

        modelMapper.map(restaurant, restaurantDTO);

        restaurantDTO.add(algaLinks.linkToRestaurants("restaurants"));

        return restaurantDTO;
    }

    @Override
    public @NotNull CollectionModel<RestaurantOnlyNameResponseDTO> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurants());
    }
}
