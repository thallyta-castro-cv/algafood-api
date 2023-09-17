package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.dtos.responses.RequestSummaryResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestSummaryResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RequestSummaryResponseDTO toModel(Request request) {
        return modelMapper.map(request, RequestSummaryResponseDTO.class);
    }

    public List<RequestSummaryResponseDTO> toCollectionModel(List<Request> requests) {
        return requests.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
