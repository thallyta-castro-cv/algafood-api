package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.exceptions.BadRequestException;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.params.ListRestaurantParams;
import br.com.thallyta.algafood.repositories.RestaurantRepository;
import br.com.thallyta.algafood.services.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAll(){
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id){
        return restaurantService.findOrFail(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant create(@RequestBody @Valid Restaurant restaurant) {
        try {
            return restaurantService.save(restaurant);
        } catch (NotFoundException e) {
            throw new BadRequestException("Restaurante não existe!");
        }
    }

    @PutMapping("/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody @Valid Restaurant restaurant) {
        Restaurant restaurantFound = restaurantService.findOrFail(id);
        BeanUtils.copyProperties(restaurant, restaurantFound, "id", "formsPayment",
                "createdDate", "updatedDate");
        try {
            return restaurantService.save(restaurantFound);
        } catch (NotFoundException e) {
            throw new BadRequestException("Restaurante não existe!");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        restaurantService.delete(id);
    }

    @GetMapping("/by-name-and-kitchen")
    public List<Restaurant> restaurantsByNameAndKitchen(@RequestParam  String name, Long kitchenId) {
        return restaurantRepository.findByNameAndKitchen(name, kitchenId);
    }

    @GetMapping("/by-shipping-fee")
    public List<Restaurant> restaurantsByShippingFee(ListRestaurantParams params) {
        return restaurantRepository.list(params);
    }

    @GetMapping("/top2-by-name")
    public List<Restaurant> restaurantsTop2ByName(String nome) {
        return restaurantRepository.findTop2ByNameContaining(nome);
    }

    @GetMapping("/count-by-kitchen")
    public int restaurantsCountByKitchen(Long kitchenId) {
        return restaurantRepository.countByKitchenId(kitchenId);
    }

    @GetMapping("/first-by-name")
    public Optional<Restaurant> restaurantByFirstName(String name) {
        return restaurantRepository.findFirstRestaurantByNameContaining(name);
    }

}
