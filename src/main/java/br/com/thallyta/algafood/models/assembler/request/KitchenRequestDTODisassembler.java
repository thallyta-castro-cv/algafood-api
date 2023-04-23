package br.com.thallyta.algafood.models.assembler.request;

import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.dtos.requests.KitchenRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Kitchen toDomainObject(KitchenRequestDTO kitchenRequestDTO) {
        return modelMapper.map(kitchenRequestDTO, Kitchen.class);
    }

    public void copyToDomainObject(KitchenRequestDTO kitchenRequestDTO, Kitchen kitchen) {
        modelMapper.map(kitchenRequestDTO, kitchen);
    }
}
