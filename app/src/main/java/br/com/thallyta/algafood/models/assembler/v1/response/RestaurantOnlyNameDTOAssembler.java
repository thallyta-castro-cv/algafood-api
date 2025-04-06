package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.RestaurantController;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantOnlyNameResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
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

    @Autowired
    private AccessService accessService;

    public RestaurantOnlyNameDTOAssembler() {
        super(RestaurantController.class, RestaurantOnlyNameResponseDTO.class);
    }

    @Override
    public @NotNull RestaurantOnlyNameResponseDTO toModel(@NotNull Restaurant restaurant) {
        RestaurantOnlyNameResponseDTO restaurantDTO = createModelWithId(
                restaurant.getId(), restaurant);

        modelMapper.map(restaurant, restaurantDTO);

        if (accessService.canGetRestaurants()) {
            restaurantDTO.add(algaLinks.linkToRestaurants("restaurants"));
        }

        return restaurantDTO;
    }

    @Override
    public @NotNull CollectionModel<RestaurantOnlyNameResponseDTO> toCollectionModel(@NotNull Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantOnlyNameResponseDTO> collectionModel = super.toCollectionModel(entities);

        if (accessService.canGetRestaurants()) {
            collectionModel.add(algaLinks.linkToRestaurants());
        }

        return collectionModel;
    }
}
