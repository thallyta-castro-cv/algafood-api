package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.ProductPhoto;
import br.com.thallyta.algafood.models.dtos.responses.ProductPhotoResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoProductResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProductPhotoResponseDTO toPhotoProductResponseDTO(ProductPhoto photoProduct) {
        return modelMapper.map(photoProduct, ProductPhotoResponseDTO.class);
    }
}
