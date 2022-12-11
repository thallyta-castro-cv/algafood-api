package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.common.exceptions.NotFound;
import br.com.thallyta.algafood.common.exceptions.ValidateMessageException;
import br.com.thallyta.algafood.model.Kitchen;
import br.com.thallyta.algafood.repositories.KitchenRepository;
import br.com.thallyta.algafood.services.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Kitchen> getById(@PathVariable Long id){
        return kitchenRepository.findById(id)
               .map(kitchen -> ResponseEntity.ok().body(kitchen))
               .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Kitchen create(@RequestBody Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        return kitchenRepository.findById(id)
                .map(kitchenFound -> {
                    BeanUtils.copyProperties(kitchen, kitchenFound, "id");
                    Kitchen kitchenUpdated = kitchenService.save(kitchenFound);
                    return ResponseEntity.ok().body(kitchenUpdated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kitchen> delete(@PathVariable Long id) {
        try {
            kitchenService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFound exception){
            return ResponseEntity.notFound().build();
        } catch (ValidateMessageException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
