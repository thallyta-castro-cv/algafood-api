package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.openapi.StateControllerOpenApi;
import br.com.thallyta.algafood.core.security.CheckSecurity;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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

    @GetMapping
    @CheckSecurity.States.CanGet
    public CollectionModel<StateResponseDTO> findAll(){
        List<State> states = stateService.getAll();
        return stateAssembler.toCollectionModel(states);
    }

    @GetMapping("/{id}")
    @CheckSecurity.States.CanGet
    public StateResponseDTO getById(@PathVariable Long id){
        State state =  stateService.findOrFail(id);
        return stateAssembler.toModel(state);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @CheckSecurity.States.CanEdit
    public StateResponseDTO create(@RequestBody @Valid StateRequestDTO stateRequestDTO) {
        State state = stateDisassembler.toDomainObject(stateRequestDTO);
        return stateAssembler.toModel(stateService.save(state));
    }

    @PutMapping("/{id}")
    @CheckSecurity.States.CanEdit
    public StateResponseDTO update(@PathVariable Long id, @RequestBody @Valid StateRequestDTO stateRequestDTO) {
        State stateFound = stateService.findOrFail(id);
        stateDisassembler.copyToDomainObject(stateRequestDTO, stateFound);
        return stateAssembler.toModel(stateService.save(stateFound)) ;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.States.CanEdit
    public ResponseEntity<Void> delete(@PathVariable Long id) {
       stateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
