package br.com.thallyta.algafood.models.assembler.request;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.dtos.requests.OrderRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Request toDomainObject(OrderRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Request.class);
    }

    public void copyToDomainObject(OrderRequestDTO requestDTO, Request request) {
        modelMapper.map(requestDTO, request);
    }
}
