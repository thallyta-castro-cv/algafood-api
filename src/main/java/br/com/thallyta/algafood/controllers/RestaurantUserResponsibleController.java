package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.controllers.openapi.RestaurantUserResponsibleControllerOpenApi;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.links.AlgaLinks;
import br.com.thallyta.algafood.models.assembler.response.UserResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.responses.UserResponseDTO;
import br.com.thallyta.algafood.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/responsible")
public class RestaurantUserResponsibleController implements RestaurantUserResponsibleControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private UserResponseDTOAssembler userResponseDTOAssembler;

    @GetMapping
    public CollectionModel<UserResponseDTO> getAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);

        CollectionModel<UserResponseDTO> userDTO = userResponseDTOAssembler
                .toCollectionModel(restaurant.getResponsible())
                .removeLinks()
                .add(algaLinks.linkToRestaurantUserResponsible(restaurantId))
                .add(algaLinks.linkToRestaurantBindResponsible(restaurantId, "bind"));

        userDTO.getContent().forEach(user -> user.add(algaLinks.linkToRestaurantUnbindResponsible(
                restaurantId, user.getId(), "unbind")));

        return userDTO;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> unbind(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.unbindResponsible(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> bind(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.bindResponsible(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }
}
