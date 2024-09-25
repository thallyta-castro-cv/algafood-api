package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.controllers.RestaurantController;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.responses.RestaurantResponseDTO;
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

    public RestaurantResponseDTOAssembler() {
        super(RestaurantController.class, RestaurantResponseDTO.class);
    }

    @Override
    public @NotNull CollectionModel<RestaurantResponseDTO> toCollectionModel(@NotNull Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurants());
    }

    @Override
    public @NotNull RestaurantResponseDTO toModel(@NotNull Restaurant restaurant) {
        RestaurantResponseDTO restaurantDTO = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantDTO);

        if (restaurant.allowsActive()) {
            restaurantDTO.add(
                    algaLinks.linkToRestaurantActive(restaurant.getId(), "active"));
        }

        if (restaurant.allowsInactive()) {
            restaurantDTO.add(
                    algaLinks.linkToRestaurantInactive(restaurant.getId(), "inactive"));
        }

        if (restaurant.allowsOpen()) {
            restaurantDTO.add(
                    algaLinks.linkToRestaurantOpen(restaurant.getId(), "open"));
        }

        if (restaurant.allowsClose()) {
            restaurantDTO.add(
                    algaLinks.linkToRestaurantClose(restaurant.getId(), "close"));
        }

        restaurantDTO.add(algaLinks.linkToProducts(restaurant.getId(), "products"));

        restaurantDTO.add(algaLinks.linkToRestaurants("restaurants"));

        restaurantDTO.add(algaLinks.linkToRestaurantFormPayment(restaurant.getId(),
                "form-payments"));

        restaurantDTO.add(algaLinks.linkToRestaurantUserResponsible(restaurant.getId(),
                "responsible"));

        return restaurantDTO;
    }
}
