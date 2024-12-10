package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.assembler.v1.request.KitchenRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.v1.response.KitchenResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.requests.KitchenRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.KitchenResponseDTO;
import br.com.thallyta.algafood.services.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenRequestDTODisassembler kitchenDisassembler;

    @Autowired
    private KitchenResponseDTOAssembler kitchenAssembler;

    @Autowired
    private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

    @GetMapping
    @CheckSecurity.Kitchen.CanGet
    public PagedModel<KitchenResponseDTO> getAll(Pageable pageable){
        Page<Kitchen> kitchens = kitchenService.getAll(pageable);
        return pagedResourcesAssembler.toModel(kitchens, kitchenAssembler);
    }

    @GetMapping("/{id}")
    @CheckSecurity.Kitchen.CanGet
    public KitchenResponseDTO getById(@PathVariable Long id){
        Kitchen kitchen = kitchenService.findOrFail(id);
        return kitchenAssembler.toModel(kitchen);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @CheckSecurity.Kitchen.CanEdit
    public KitchenResponseDTO create(@RequestBody @Valid KitchenRequestDTO kitchenRequestDTO) {
        Kitchen kitchen = kitchenDisassembler.toDomainObject(kitchenRequestDTO);
        return kitchenAssembler.toModel(kitchenService.save(kitchen));
    }

    @PutMapping("/{id}")
    @CheckSecurity.Kitchen.CanEdit
    public KitchenResponseDTO update(@PathVariable Long id, @RequestBody @Valid KitchenRequestDTO kitchenRequestDTO) {
        Kitchen kitchenFound = kitchenService.findOrFail(id);
        kitchenFound.setName(kitchenRequestDTO.getName());
        kitchenDisassembler.toModel(kitchenFound);
        return kitchenAssembler.toModel(kitchenService.save(kitchenFound));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Kitchen.CanEdit
    public void delete(@PathVariable Long id) {
            kitchenService.delete(id);
    }

}
