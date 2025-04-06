package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.RestaurantController;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantBasicResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
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

    @Autowired
    private AccessService accessService;

    public RestaurantBasicResponseDTOAssembler() {
        super(RestaurantController.class, RestaurantBasicResponseDTO.class);
    }

    @Override
    public @NotNull RestaurantBasicResponseDTO toModel(@NotNull Restaurant restaurant) {
        RestaurantBasicResponseDTO restaurantDTO = createModelWithId(
                restaurant.getId(), restaurant);

        modelMapper.map(restaurant, restaurantDTO);

        if (accessService.canGetRestaurants()){
            restaurantDTO.add(algaLinks.linkToRestaurants("restaurants"));
        }

        if (accessService.canGetKitchens()) {
            restaurantDTO.getKitchen().add(
                    algaLinks.linkToKitchen(restaurant.getKitchen().getId()));
        }

        return restaurantDTO;
    }

    @Override
    public @NotNull CollectionModel<RestaurantBasicResponseDTO> toCollectionModel(@NotNull Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantBasicResponseDTO> collectionModel = super.toCollectionModel(entities);

        if (accessService.canGetRestaurants()) {
            collectionModel.add(algaLinks.linkToRestaurants());
        }

        return collectionModel;
    }
}
