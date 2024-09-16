package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.controllers.openapi.StateControllerOpenApi;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.models.assembler.request.StateRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.StateResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.StateRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.StateResponseDTO;
import br.com.thallyta.algafood.repositories.StateRepository;
import br.com.thallyta.algafood.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/states")
public class StateController implements StateControllerOpenApi {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @Autowired
    private StateRequestDTODisassembler stateDisassembler;

    @Autowired
    private StateResponseDTOAssembler stateAssembler;

    @GetMapping
    public CollectionModel<StateResponseDTO> findAll(){
        List<State> states = stateService.getAll();
        return stateAssembler.toCollectionModel(states);
    }

    @GetMapping("/{id}")
    public StateResponseDTO getById(@PathVariable Long id){
        State state =  stateService.findOrFail(id);
        return stateAssembler.toStateResponse(state);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public StateResponseDTO create(@RequestBody @Valid StateRequestDTO stateRequestDTO) {
        State state = stateDisassembler.toDomainObject(stateRequestDTO);
        return stateAssembler.toStateResponse(stateService.save(state));
    }

    @PutMapping("/{id}")
    public StateResponseDTO update(@PathVariable Long id, @RequestBody @Valid StateRequestDTO stateRequestDTO) {
        State stateFound = stateService.findOrFail(id);
        stateDisassembler.copyToDomainObject(stateRequestDTO, stateFound);
        return stateAssembler.toStateResponse(stateService.save(stateFound)) ;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
       stateService.delete(id);
    }
}
