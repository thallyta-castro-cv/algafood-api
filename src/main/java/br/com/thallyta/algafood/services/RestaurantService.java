package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.FormPayment;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private FormPaymentService formPaymentService;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long cityId = restaurant.getAddress().getCity().getId();
        City city = cityService.findOrFail(cityId);
        restaurant.getAddress().setCity(city);
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void delete(Long id){
        try{
            restaurantRepository.deleteById(id);
            restaurantRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Restaurante não encontrado!");
        }
    }

    public Restaurant findOrFail(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurante não encontrado!"));
    }

    @Transactional
    public void active(Long restaurantId){
        Restaurant restaurant = findOrFail(restaurantId);
        restaurant.setActive(true);
    }

    @Transactional
    public void inactive(Long restaurantId){
        Restaurant restaurant = findOrFail(restaurantId);
        restaurant.setActive(false);
    }

    @Transactional
    public void unbindFormPayment(Long restaurantId, Long formPaymentId) {
        Restaurant restaurant = findOrFail(restaurantId);
        FormPayment formPayment = formPaymentService.findOrFail(formPaymentId);
        restaurant.getFormsPayment().remove(formPayment);
    }

    @Transactional
    public void bindFormPayment(Long restaurantId, Long formPaymentId) {
        Restaurant restaurant = findOrFail(restaurantId);
        FormPayment formPayment = formPaymentService.findOrFail(formPaymentId);
        restaurant.getFormsPayment().add(formPayment);
    }

    @Transactional
    public void open(Long restaurantId) {
        Restaurant restaurant = findOrFail(restaurantId);
        restaurant.setOpen(true);
    }

    @Transactional
    public void close(Long restaurantId) {
        Restaurant restaurant = findOrFail(restaurantId);
        restaurant.setOpen(false);
    }


}
