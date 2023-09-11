package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.dtos.responses.RequestResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RequestResponseDTO toModel(Request request) {
        return modelMapper.map(request, RequestResponseDTO.class);
    }

    public List<RequestResponseDTO> toCollectionModel(List<Request> requests) {
        return requests.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
