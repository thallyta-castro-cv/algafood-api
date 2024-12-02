package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.RestaurantController;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantResponseDTOAssembler extends
        RepresentationModelAssemblerSupport<Restaurant, RestaurantResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AccessService accessService;

    public RestaurantResponseDTOAssembler() {
        super(RestaurantController.class, RestaurantResponseDTO.class);
    }

    @Override
    public @NotNull CollectionModel<RestaurantResponseDTO> toCollectionModel(@NotNull Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantResponseDTO> collectionModel = super.toCollectionModel(entities);

        if (accessService.canGetRestaurants()) {
            collectionModel.add(algaLinks.linkToRestaurants());
        }

        return collectionModel;
    }

    @Override
    public @NotNull RestaurantResponseDTO toModel(@NotNull Restaurant restaurant) {
        RestaurantResponseDTO restaurantDTO = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantDTO);

        if (accessService.canManageRegisterRestaurants()) {
            if (restaurant.allowsActive()) {
                restaurantDTO.add(
                        algaLinks.linkToRestaurantActive(restaurant.getId(), "active"));
            }

            if (restaurant.allowsInactive()) {
                restaurantDTO.add(
                        algaLinks.linkToRestaurantInactive(restaurant.getId(), "inactive"));
            }
        }

        if (accessService.canManageOperationRestaurant(restaurant.getId())) {
            if (restaurant.allowsOpen()) {
                restaurantDTO.add(
                        algaLinks.linkToRestaurantOpen(restaurant.getId(), "open"));
            }

            if (restaurant.allowsClose()) {
                restaurantDTO.add(
                        algaLinks.linkToRestaurantClose(restaurant.getId(), "close"));
            }
        }

        if (accessService.canGetRestaurants()){
            restaurantDTO.add(algaLinks.linkToProducts(restaurant.getId(), "products"));
        }

        if (accessService.canGetRestaurants()){
            restaurantDTO.add(algaLinks.linkToRestaurants("restaurants"));
        }

        if (accessService.canGetRestaurants()) {
            restaurantDTO.add(algaLinks.linkToRestaurantFormPayment(restaurant.getId(),
                    "form-payments"));
        }

        if (accessService.canManageRegisterRestaurants()){
            restaurantDTO.add(algaLinks.linkToRestaurantUserResponsible(restaurant.getId(),
                    "responsible"));
        }

        return restaurantDTO;
    }
}
