package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.assembler.response.RequestResponseDTOAssembler;
import br.com.thallyta.algafood.models.assembler.response.RequestSummaryResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.responses.RequestResponseDTO;
import br.com.thallyta.algafood.models.dtos.responses.RequestSummaryResponseDTO;
import br.com.thallyta.algafood.repositories.RequestRepository;
import br.com.thallyta.algafood.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestResponseDTOAssembler responseDTOAssembler;

    @Autowired
    private RequestSummaryResponseDTOAssembler responseSummaryDTOAssembler;

    @GetMapping
    public List<RequestSummaryResponseDTO> getAll() {
        List<Request> requests = requestRepository.findAll();
        return responseSummaryDTOAssembler.toCollectionModel(requests);
    }

    @GetMapping("/{requestId}")
    public RequestResponseDTO getById(@PathVariable Long requestId) {
        Request request = requestService.findOrFail(requestId);
        return responseDTOAssembler.toModel(request);
    }
}
