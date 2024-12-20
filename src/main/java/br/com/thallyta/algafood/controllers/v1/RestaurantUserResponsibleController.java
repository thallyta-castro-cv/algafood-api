package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.openapi.RestaurantUserResponsibleControllerOpenApi;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.assembler.v1.response.UserResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.responses.UserResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
import br.com.thallyta.algafood.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/restaurants/{restaurantId}/responsible")
public class RestaurantUserResponsibleController implements RestaurantUserResponsibleControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private UserResponseDTOAssembler userResponseDTOAssembler;

    @Autowired
    private AccessService accessService;

    @GetMapping
    @CheckSecurity.Restaurants.CanGet
    public CollectionModel<UserResponseDTO> getAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);

        CollectionModel<UserResponseDTO> userDTO = userResponseDTOAssembler
                .toCollectionModel(restaurant.getResponsible())
                .removeLinks()
                .add(algaLinks.linkToRestaurantUserResponsible(restaurantId));

        if (accessService.canManageRegisterRestaurants()) {
             userDTO.add(algaLinks.linkToRestaurantBindResponsible(restaurantId, "bind"));

            userDTO.getContent().forEach(user -> user.add(algaLinks.linkToRestaurantUnbindResponsible(
                    restaurantId, user.getId(), "unbind")));
        }

        return userDTO;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanEdit
    public ResponseEntity<Void> unbind(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.unbindResponsible(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanEdit
    public ResponseEntity<Void> bind(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.bindResponsible(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }
}
