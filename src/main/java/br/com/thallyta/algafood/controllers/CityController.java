package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.common.exceptions.NotFound;
import br.com.thallyta.algafood.common.exceptions.ValidateMessageException;
import br.com.thallyta.algafood.model.City;
import br.com.thallyta.algafood.repositories.CityRepository;
import br.com.thallyta.algafood.services.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> getAll(){
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@PathVariable Long id){
        return cityRepository.findById(id)
                .map(restaurant -> ResponseEntity.ok().body(restaurant))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody City city) {
        try{
            city = cityService.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).body(city);
        } catch(ValidateMessageException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @RequestBody City city) {
        return cityRepository.findById(id)
                .map(cityFound -> {
                    BeanUtils.copyProperties(city, cityFound, "id");
                    City cityUpdated = cityService.save(cityFound);
                    return ResponseEntity.ok().body(cityUpdated);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> delete(@PathVariable Long id) {
        try {
            cityService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFound exception){
            return ResponseEntity.notFound().build();
        } catch (ValidateMessageException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
