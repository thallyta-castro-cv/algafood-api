package br.com.thallyta.algafood.models.assembler.v2.request;

import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.dtos.v2.request.KitchenRequestV2DTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenRequestDTODisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public Kitchen toDomainObject(KitchenRequestV2DTO kitchenRequestDTO) {
        return modelMapper.map(kitchenRequestDTO, Kitchen.class);
    }

    public void copyToDomainObject(KitchenRequestV2DTO kitchenRequestDTO, Kitchen kitchen) {
        modelMapper.map(kitchenRequestDTO, kitchen);
    }
}
