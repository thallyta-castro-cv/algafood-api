package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.repositories.StateRepository;
import br.com.thallyta.algafood.services.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public State getById(@PathVariable Long id){
        return stateService.findOrFail(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public State create(@RequestBody State state) {
        return stateService.save(state);
    }

    @PutMapping("/{id}")
    public State update(@PathVariable Long id, @RequestBody State state) {
        State stateFound = stateService.findOrFail(id);
        BeanUtils.copyProperties(state, stateFound, "id");
        return  stateService.save(stateFound);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
       stateService.delete(id);
    }
}
