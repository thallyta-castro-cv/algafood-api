package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.repositories.KitchenRepository;
import br.com.thallyta.algafood.services.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> getAll(){
        return kitchenService.getAll();
    }

    @GetMapping("/{id}")
    public Kitchen getById(@PathVariable Long id){
        return kitchenService.findOrFail(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Kitchen create(@RequestBody @Valid Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    public Kitchen update(@PathVariable Long id, @RequestBody @Valid Kitchen kitchen) {
        Kitchen kitchenFound = kitchenService.findOrFail(id);
        BeanUtils.copyProperties(kitchen, kitchenFound, "id");
        return  kitchenService.save(kitchenFound);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
            kitchenService.delete(id);
    }

    @GetMapping("/search-name")
    public List<Kitchen> getByName(@RequestParam String name){
        return kitchenRepository.getByName(name);
    }

    @GetMapping("/search-name-all")
    public List<Kitchen> getByNameContaining(@RequestParam String name){
        return kitchenRepository.findAllByNameContaining(name);
    }

    @GetMapping("/exists")
    public boolean kitchenExists(String name) {
        return kitchenRepository.existsByName(name);
    }
}
