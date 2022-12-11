package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.common.exceptions.NotFound;
import br.com.thallyta.algafood.common.exceptions.ValidateMessageException;
import br.com.thallyta.algafood.model.Restaurant;
import br.com.thallyta.algafood.repositories.KitchenRepository;
import br.com.thallyta.algafood.repositories.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant save(Restaurant restaurant) {
        try{
            Long kitchenId = restaurant.getKitchen().getId();
            kitchenRepository.findById(kitchenId);
            return restaurantRepository.save(restaurant);
        } catch(DataIntegrityViolationException exception){
            throw new ValidateMessageException("Não foi encontrado restaurante com o id cadastrado.");
        }
    }

    public void delete(Long id){
        try{
            restaurantRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFound("Restaurante não encontrado!");
        }
    }

    public void merge(Map<String, Object> sourceData, Restaurant destinationRestaurant) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant originRestaurant = objectMapper.convertValue(sourceData, Restaurant.class);

        sourceData.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, originRestaurant);

            ReflectionUtils.setField(field, destinationRestaurant, novoValor);
        });
    }
}
