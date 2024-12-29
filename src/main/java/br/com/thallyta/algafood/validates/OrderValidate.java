package br.com.thallyta.algafood.validates;

import br.com.thallyta.algafood.core.exceptions.BadRequestException;
import br.com.thallyta.algafood.models.*;
import br.com.thallyta.algafood.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderValidate {

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FormPaymentService formPaymentService;

    @Autowired
    private RestaurantService restaurantService;

    public void validateRequest(Request request) {
        City city = cityService.findOrFail(request.getAddress().getCity().getId());
        UserSystem client = userService.findOrFail(request.getClient().getId());
        Restaurant restaurant = restaurantService.findOrFail(request.getRestaurant().getId());
        FormPayment formPayment = formPaymentService.findOrFail(request.getFormPayment().getId());

        request.getAddress().setCity(city);
        request.setClient(client);
        request.setRestaurant(restaurant);
        request.setFormPayment(formPayment);

        if (restaurant.notAcceptPaymentMethod(formPayment)) {
            throw new BadRequestException("A forma de pagamento informada não é aceita pelo restaurante");
        }
    }

    public void validateItems(Request request) {
        request.getItems().forEach(item -> {
            Product product = productService.findOrFail(
                    request.getRestaurant().getId(), item.getProduct().getId());

            item.setRequest(request);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
        });
    }
}