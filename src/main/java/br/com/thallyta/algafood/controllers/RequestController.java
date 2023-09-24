package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.assembler.request.RequestRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.RequestResponseDTOAssembler;
import br.com.thallyta.algafood.models.assembler.response.RequestSummaryResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.RequestRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.RequestResponseDTO;
import br.com.thallyta.algafood.models.params.ListRequestParams;
import br.com.thallyta.algafood.repositories.RequestRepository;
import br.com.thallyta.algafood.repositories.specs.RequestSpecs;
import br.com.thallyta.algafood.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private RequestRequestDTODisassembler requestDTODisassembler;


    @GetMapping
    public List<RequestResponseDTO> getAll(ListRequestParams params) {
        List<Request> requests = requestRepository.findAll(RequestSpecs.usingParams(params));
        return responseDTOAssembler.toCollectionModel(requests);
    }

    @GetMapping("/{requestId}")
    public RequestResponseDTO getById(@PathVariable String code) {
        Request request = requestService.findOrFail(code);
        return responseDTOAssembler.toModel(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestResponseDTO createRequest(@Valid  @RequestBody RequestRequestDTO requestDTO) {
        try {
            Request newRequest = requestDTODisassembler.toDomainObject(requestDTO);
            newRequest.setClient(new User());
            newRequest.getClient().setId(1L);
            newRequest = requestService.issueRequest(newRequest);
            return responseDTOAssembler.toModel(newRequest);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
