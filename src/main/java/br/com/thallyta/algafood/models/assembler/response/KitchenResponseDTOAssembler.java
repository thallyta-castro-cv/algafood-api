package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.controllers.KitchenController;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.dtos.responses.KitchenResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class KitchenResponseDTOAssembler extends RepresentationModelAssemblerSupport<Kitchen, KitchenResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenResponseDTOAssembler() {
        super(KitchenController.class, KitchenResponseDTO.class);
    }

    @Override
    public @NotNull KitchenResponseDTO toModel(@NotNull Kitchen kitchen) {
        KitchenResponseDTO kitchenDTO = modelMapper.map(kitchen, KitchenResponseDTO.class);
        kitchenDTO.add(linkTo(KitchenController.class)
                .slash(kitchenDTO.getId()).withSelfRel());
        kitchenDTO.add(linkTo(KitchenController.class).withRel("kitchens"));
        return kitchenDTO;
    }
}
