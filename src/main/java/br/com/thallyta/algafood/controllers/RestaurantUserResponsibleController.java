package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.response.UserResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.responses.UserResponseDTO;
import br.com.thallyta.algafood.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/responsible")
public class RestaurantUserResponsibleController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserResponseDTOAssembler userResponseDTOAssembler;

    @GetMapping
    public List<UserResponseDTO> getAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        return userResponseDTOAssembler.toCollectionModel(restaurant.getResponsible());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbind(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.unbindResponsible(restaurantId, userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bind(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.bindResponsible(restaurantId, userId);
    }
}
