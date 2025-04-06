package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.openapi.RestaurantFormPaymentControllerOpenApi;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.assembler.v1.response.FormPaymentResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.responses.FormPaymentResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
import br.com.thallyta.algafood.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/restaurants/{restaurantId}/form-payments")
public class RestaurantFormPaymentsController implements RestaurantFormPaymentControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FormPaymentResponseDTOAssembler formPaymentResponseDTOAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AccessService accessService;

    @GetMapping
    @CheckSecurity.Restaurants.CanGet
    public CollectionModel<FormPaymentResponseDTO> getAll(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);

        CollectionModel<FormPaymentResponseDTO> formPaymentResponseDTO =
                formPaymentResponseDTOAssembler.toCollectionModel(
                        restaurant.getFormsPayment()).removeLinks()
                        .add(algaLinks.linkToRestaurantFormPayments(restaurantId));

        if (accessService.canManageOperationRestaurant(restaurantId)) {
            formPaymentResponseDTO.add(algaLinks.linkToRestaurantFormPaymentBind(restaurantId, "bind"));

            formPaymentResponseDTO.getContent().forEach(formPayment ->
                    formPayment.add(algaLinks.linkToRestaurantFormPaymentUnbind(
                            restaurantId, formPayment.getId(), "unbind")));
        }

        return formPaymentResponseDTO;
    }

    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanManagerOperation
    public ResponseEntity<Void> unbind(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.unbindFormPayment(restaurantId, formPaymentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanManagerOperation
    public ResponseEntity<Void> bind(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.bindFormPayment(restaurantId, formPaymentId);
        return ResponseEntity.noContent().build();
    }
}
