package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.common.exceptions.NotFound;
import br.com.thallyta.algafood.common.exceptions.ValidateMessageException;
import br.com.thallyta.algafood.model.Kitchen;
import br.com.thallyta.algafood.model.Restaurant;
import br.com.thallyta.algafood.repositories.RestaurantRepository;
import br.com.thallyta.algafood.services.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Restaurant> getById(@PathVariable Long id){
        return restaurantRepository.findById(id)
                .map(restaurant -> ResponseEntity.ok().body(restaurant))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Restaurant restaurant) {
        try{
            restaurant = restaurantService.save((restaurant));
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch(ValidateMessageException exception){
          return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return restaurantRepository.findById(id)
                .map(restaurantFound -> {
                    BeanUtils.copyProperties(restaurant, restaurantFound, "id");
                    Restaurant restaurantUpdated = restaurantService.save(restaurantFound);
                    return ResponseEntity.ok().body(restaurantUpdated);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate (@PathVariable Long id, @RequestBody Map<String, Object> fields){
        return restaurantRepository.findById(id)
                .map(restaurantFound -> {
                    restaurantService.merge(fields, restaurantFound);
                    Restaurant restaurantUpdated = update(id, restaurantFound).getBody();
                    return ResponseEntity.ok().body(restaurantUpdated);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kitchen> delete(@PathVariable Long id) {
        try {
            restaurantService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFound exception){
            return ResponseEntity.notFound().build();
        }
    }

}
