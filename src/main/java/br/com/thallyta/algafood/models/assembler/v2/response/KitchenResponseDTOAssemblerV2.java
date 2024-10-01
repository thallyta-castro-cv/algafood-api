package br.com.thallyta.algafood.models.assembler.v2.response;

import br.com.thallyta.algafood.controllers.v2.KitchenControllerV2;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.dtos.v2.links.AlgaLinksV2;
import br.com.thallyta.algafood.models.dtos.v2.response.KitchenResponseV2DTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class KitchenResponseDTOAssemblerV2
        extends RepresentationModelAssemblerSupport<Kitchen, KitchenResponseV2DTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinksV2 algaLinks;

    public KitchenResponseDTOAssemblerV2() {
        super(KitchenControllerV2.class, KitchenResponseV2DTO.class);
    }

    @Override
    public @NotNull KitchenResponseV2DTO toModel(@NotNull Kitchen kitchen) {
        KitchenResponseV2DTO kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);
        kitchenModel.add(algaLinks.linkToKitchens("kitchen"));
        return kitchenModel;
    }
}
