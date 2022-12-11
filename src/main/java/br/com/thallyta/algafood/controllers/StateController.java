package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.common.exceptions.NotFound;
import br.com.thallyta.algafood.model.State;
import br.com.thallyta.algafood.repositories.StateRepository;
import br.com.thallyta.algafood.services.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> getAll(){
        return stateService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getById(@PathVariable Long id){
        return stateRepository.findById(id)
                .map(state -> ResponseEntity.ok().body(state))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public State create(@RequestBody State state) {
        return stateService.save(state);
    }

    @PutMapping("/{id}")
    public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state) {
        return stateRepository.findById(id)
                .map(stateFound -> {
                    BeanUtils.copyProperties(state, stateFound, "id");
                    State stateUpdated = stateService.save(stateFound);
                    return ResponseEntity.ok().body(stateUpdated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            stateService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFound exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
