package br.com.thallyta.algafood.models.assembler.v1.request;

import br.com.thallyta.algafood.controllers.v1.KitchenController;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.dtos.v1.requests.KitchenRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.KitchenResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class KitchenRequestDTODisassembler extends RepresentationModelAssemblerSupport<Kitchen, KitchenResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenRequestDTODisassembler() {
        super(KitchenController.class, KitchenResponseDTO.class);
    }

    public Kitchen toDomainObject(KitchenRequestDTO kitchenRequestDTO) {
        return modelMapper.map(kitchenRequestDTO, Kitchen.class);
    }

    public void copyToDomainObject(KitchenRequestDTO kitchenRequestDTO, Kitchen kitchen) {
        modelMapper.map(kitchenRequestDTO, kitchen);
    }

    @Override
    public @NotNull KitchenResponseDTO toModel(@NotNull Kitchen kitchen) {
        KitchenResponseDTO kitchenResponseDTO = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, KitchenResponseDTO.class);
        kitchenResponseDTO.add((linkTo(KitchenController.class).withRel("kitchens")));
        return kitchenResponseDTO;
    }
}
