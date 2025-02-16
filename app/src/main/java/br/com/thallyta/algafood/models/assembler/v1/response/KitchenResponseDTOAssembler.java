package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.KitchenController;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.KitchenResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class KitchenResponseDTOAssembler extends RepresentationModelAssemblerSupport<Kitchen, KitchenResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AccessService accessService;

    public KitchenResponseDTOAssembler() {
        super(KitchenController.class, KitchenResponseDTO.class);
    }

    @Override
    public @NotNull KitchenResponseDTO toModel(@NotNull Kitchen kitchen) {
        KitchenResponseDTO kitchenDTO = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenDTO);

        if (accessService.canGetKitchens()) {
            kitchenDTO.add(algaLinks.linkToKitchens("kitchens"));
        }

        return kitchenDTO;
    }
}
