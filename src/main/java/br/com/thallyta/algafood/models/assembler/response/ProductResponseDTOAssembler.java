package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.Product;
import br.com.thallyta.algafood.models.dtos.responses.ProductResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProductResponseDTO toProductResponse(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public List<ProductResponseDTO> toCollectionModel(List<Product> products) {
        return products.stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
    }
}
