package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.controllers.openapi.RestaurantFormPaymentsControllerOpenApi;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.response.FormPaymentResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.responses.FormPaymentResponseDTO;
import br.com.thallyta.algafood.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/form-payments")
public class RestaurantFormPaymentsController implements RestaurantFormPaymentsControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FormPaymentResponseDTOAssembler formPaymentResponseDTOAssembler;

    @GetMapping
    public List<FormPaymentResponseDTO> getAll(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        return formPaymentResponseDTOAssembler.toCollectionModel(restaurant.getFormsPayment());
    }

    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbind(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.unbindFormPayment(restaurantId, formPaymentId);
    }

    @PutMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bind(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.bindFormPayment(restaurantId, formPaymentId);
    }
}
