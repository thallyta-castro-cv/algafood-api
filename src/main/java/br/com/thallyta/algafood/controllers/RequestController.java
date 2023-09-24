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
import br.com.thallyta.algafood.repositories.RequestRepository;
import br.com.thallyta.algafood.services.RequestService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
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


    /**
     * Filtra os dados do DTO de acordo com os filtros informados pelo consumidor da API.
     * Usa JsonFilter para filtrar os campos
     * @param fields - Array de strings contendo os nomes dos campos a serem filtrados.
     * @return O DTO filtrado, ou o DTO completo se nenhum filtro for informado.
     */
    @GetMapping
    public MappingJacksonValue getAll(@RequestParam(required = false) String fields) {
        List<Request> requests = requestRepository.findAll();
        List<RequestSummaryResponseDTO> requestsDTO = responseSummaryDTOAssembler.toCollectionModel(requests);

        MappingJacksonValue requestsWrapper = new MappingJacksonValue(requestsDTO);
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("requestFilter", SimpleBeanPropertyFilter.serializeAll());

        if (StringUtils.isNotBlank(fields)) {
            filterProvider.addFilter("requestFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
        }

        requestsWrapper.setFilters(filterProvider);
        return requestsWrapper;
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
