package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.data.PageWrapper;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.controllers.openapi.OrderControllerOpenApi;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.assembler.request.OrderRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.OrderResponseDTOAssembler;
import br.com.thallyta.algafood.models.assembler.response.OrderSummaryResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.OrderRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.OrderResponseDTO;
import br.com.thallyta.algafood.models.dtos.responses.OrderSummaryResponseDTO;
import br.com.thallyta.algafood.models.params.ListRequestParams;
import br.com.thallyta.algafood.repositories.RequestRepository;
import br.com.thallyta.algafood.repositories.specs.RequestSpecs;
import br.com.thallyta.algafood.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/requests")
public class OrderController implements OrderControllerOpenApi {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderResponseDTOAssembler responseDTOAssembler;

    @Autowired
    private OrderSummaryResponseDTOAssembler responseSummaryDTOAssembler;

    @Autowired
    private OrderRequestDTODisassembler requestDTODisassembler;

    @Autowired
    private PagedResourcesAssembler<Request> pagedResourcesAssembler;


    @GetMapping
    public PagedModel<OrderSummaryResponseDTO> getAll(ListRequestParams params,
                                                      @PageableDefault(size = 10) Pageable pageable) {
        Pageable pageableTranslate = orderService.translatePageable(pageable);
        Page<Request> ordersPage = requestRepository.findAll(RequestSpecs.usingParams(params), pageableTranslate);
        ordersPage = new PageWrapper<>(ordersPage, pageable);
        return pagedResourcesAssembler.toModel(ordersPage, responseSummaryDTOAssembler);
    }

    @GetMapping("/{requestId}")
    public OrderResponseDTO getById(@PathVariable String code) {
        Request request = orderService.findOrFail(code);
        return responseDTOAssembler.toModel(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO createRequest(@Valid  @RequestBody OrderRequestDTO requestDTO) {
        try {
            Request newRequest = requestDTODisassembler.toDomainObject(requestDTO);
            newRequest.setClient(new User());
            newRequest.getClient().setId(1L);
            newRequest = orderService.issueRequest(newRequest);
            return responseDTOAssembler.toModel(newRequest);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
