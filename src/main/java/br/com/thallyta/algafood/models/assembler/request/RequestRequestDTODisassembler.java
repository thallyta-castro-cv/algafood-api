package br.com.thallyta.algafood.models.assembler.request;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.dtos.requests.RequestRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Request toDomainObject(RequestRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Request.class);
    }

    public void copyToDomainObject(RequestRequestDTO requestDTO, Request request) {
        modelMapper.map(requestDTO, request);
    }
}
