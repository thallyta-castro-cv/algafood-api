package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.assembler.request.RequestRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.RequestResponseDTOAssembler;
import br.com.thallyta.algafood.models.assembler.response.RequestSummaryResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.RequestRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.RequestResponseDTO;
import br.com.thallyta.algafood.models.dtos.responses.RequestSummaryResponseDTO;
import br.com.thallyta.algafood.models.params.ListRequestParams;
import br.com.thallyta.algafood.repositories.RequestRepository;
import br.com.thallyta.algafood.repositories.specs.RequestSpecs;
import br.com.thallyta.algafood.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<RequestSummaryResponseDTO> getAll(ListRequestParams params,
                                                            @PageableDefault(size = 10) Pageable pageable) {
        pageable = requestService.translatePageable(pageable);
        Page<Request> requests = requestRepository.findAll(RequestSpecs.usingParams(params), pageable);
        List<RequestSummaryResponseDTO> requestsDTO = responseSummaryDTOAssembler.toCollectionModel(requests.getContent());
        return new PageImpl<>(requestsDTO, pageable, requests.getTotalElements());
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
