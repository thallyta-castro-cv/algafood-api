package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.dtos.responses.KitchenResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KitchenResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenResponseDTO toKitchenResponse(Kitchen kitchen) {
        return modelMapper.map(kitchen, KitchenResponseDTO.class);
    }

    public List<KitchenResponseDTO> toCollectionModel(List<Kitchen> kitchens) {
        return kitchens.stream()
                .map(kitchen -> toKitchenResponse(kitchen))
                .collect(Collectors.toList());
    }
}
