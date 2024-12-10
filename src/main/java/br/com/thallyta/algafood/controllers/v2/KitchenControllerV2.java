package br.com.thallyta.algafood.controllers.v2;

import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.assembler.v2.request.KitchenRequestDTODisassemblerV2;
import br.com.thallyta.algafood.models.assembler.v2.response.KitchenResponseDTOAssemblerV2;
import br.com.thallyta.algafood.models.dtos.v2.request.KitchenRequestV2DTO;
import br.com.thallyta.algafood.models.dtos.v2.response.KitchenResponseV2DTO;
import br.com.thallyta.algafood.repositories.KitchenRepository;
import br.com.thallyta.algafood.services.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v2/kitchens")
public class KitchenControllerV2 {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenResponseDTOAssemblerV2 kitchenAssembler;

    @Autowired
    private KitchenRequestDTODisassemblerV2 kitchenDisassembler;

    @Autowired
    private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<KitchenResponseV2DTO> list(@PageableDefault(size = 10) Pageable pageable) {
        Page<Kitchen> cozinhasPage = kitchenRepository.findAll(pageable);

        return pagedResourcesAssembler
                .toModel(cozinhasPage, kitchenAssembler);
    }

    @GetMapping("/{kitchenId}")
    public KitchenResponseV2DTO getById(@PathVariable Long kitchenId) {
        Kitchen kitchen = kitchenService.findOrFail(kitchenId);
        return kitchenAssembler.toModel(kitchen);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenResponseV2DTO add(@RequestBody @Valid KitchenRequestV2DTO kitchenRequestDTO) {
        Kitchen kitchen = kitchenDisassembler.toDomainObject(kitchenRequestDTO);
        kitchen = kitchenService.save(kitchen);
        return kitchenAssembler.toModel(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public KitchenResponseV2DTO update(@PathVariable Long kitchenId,
                                       @RequestBody @Valid KitchenRequestV2DTO kitchenRequest) {
        Kitchen kitchen = kitchenService.findOrFail(kitchenId);
        kitchenDisassembler.copyToDomainObject(kitchenRequest, kitchen);
        kitchen = kitchenService.save(kitchen);
        return kitchenAssembler.toModel(kitchen);
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long kitchenId) {
        kitchenService.delete(kitchenId);
    }
}
