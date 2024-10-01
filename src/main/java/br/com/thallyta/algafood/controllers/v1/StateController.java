package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.v1.openapi.StateControllerOpenApi;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.models.assembler.v1.request.StateRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.v1.response.StateResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.requests.StateRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.StateResponseDTO;
import br.com.thallyta.algafood.repositories.StateRepository;
import br.com.thallyta.algafood.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/states")
public class StateController implements StateControllerOpenApi {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @Autowired
    private StateRequestDTODisassembler stateDisassembler;

    @Autowired
    private StateResponseDTOAssembler stateAssembler;

    @Override
    @GetMapping
    public CollectionModel<StateResponseDTO> findAll(){
        List<State> states = stateService.getAll();
        return stateAssembler.toCollectionModel(states);
    }

    @Override
    @GetMapping("/{id}")
    public StateResponseDTO getById(@PathVariable Long id){
        State state =  stateService.findOrFail(id);
        return stateAssembler.toModel(state);
    }

    @Override
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public StateResponseDTO create(@RequestBody @Valid StateRequestDTO stateRequestDTO) {
        State state = stateDisassembler.toDomainObject(stateRequestDTO);
        return stateAssembler.toModel(stateService.save(state));
    }

    @Override
    @PutMapping("/{id}")
    public StateResponseDTO update(@PathVariable Long id, @RequestBody @Valid StateRequestDTO stateRequestDTO) {
        State stateFound = stateService.findOrFail(id);
        stateDisassembler.copyToDomainObject(stateRequestDTO, stateFound);
        return stateAssembler.toModel(stateService.save(stateFound)) ;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
       stateService.delete(id);
    }
}
