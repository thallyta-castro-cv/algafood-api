package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.common.exceptions.BadRequestException;
import br.com.thallyta.algafood.common.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.repositories.CityRepository;
import br.com.thallyta.algafood.services.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public City getById(@PathVariable Long id){
        return cityService.findOrFail(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City create(@RequestBody City city) {
        try {
            return cityService.save(city);
        } catch (NotFoundException e) {
            throw new BadRequestException("Estado não existe!");
        }
    }

    @PutMapping("/{id}")
    public City update(@PathVariable Long id, @RequestBody City city) {
        City cityFound = cityService.findOrFail(id);
        BeanUtils.copyProperties(city, cityFound, "id");

        try {
            return cityService.save(cityFound);
        } catch (NotFoundException e) {
            throw new BadRequestException("Estado não existe!");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}
