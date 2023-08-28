package br.com.thallyta.algafood.models.assembler.request;

import br.com.thallyta.algafood.models.Product;
import br.com.thallyta.algafood.models.dtos.requests.ProductRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ProductRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Product toDomainObject(ProductRequestDTO productRequestDTO) {
        return modelMapper.map(productRequestDTO, Product.class);
    }

    public void copyToDomainObject(@Valid ProductRequestDTO productRequestDTO, Product product) {
        modelMapper.map(productRequestDTO, product);
    }
}
