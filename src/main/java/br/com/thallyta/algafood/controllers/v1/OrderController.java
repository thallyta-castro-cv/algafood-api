package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.v1.openapi.OrderControllerOpenApi;
import br.com.thallyta.algafood.core.data.PageWrapper;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.models.assembler.v1.request.OrderRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.v1.response.OrderResponseDTOAssembler;
import br.com.thallyta.algafood.models.assembler.v1.response.OrderSummaryResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.requests.OrderRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.OrderResponseDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.OrderSummaryResponseDTO;
import br.com.thallyta.algafood.models.params.ListRequestParams;
import br.com.thallyta.algafood.repositories.RequestRepository;
import br.com.thallyta.algafood.repositories.specs.RequestSpecs;
import br.com.thallyta.algafood.services.AccessService;
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
@RequestMapping(value = "/v1/requests")
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

    @Autowired
    private AccessService accessService;

    @GetMapping
    public PagedModel<OrderSummaryResponseDTO> getAll(ListRequestParams params,
                                                      @PageableDefault(size = 10) Pageable pageable) {
        Pageable pageableTranslate = orderService.translatePageable(pageable);
        Page<Request> ordersPage = requestRepository.findAll(RequestSpecs.usingParams(params), pageableTranslate);
        ordersPage = new PageWrapper<>(ordersPage, pageable);
         return pagedResourcesAssembler.toModel(ordersPage, responseSummaryDTOAssembler);
    }

    @GetMapping("/{code}")
    @CheckSecurity.Order.CanGetUnique
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
            newRequest.getClient().setId(accessService.getUserId());
            newRequest = orderService.issueRequest(newRequest);
            return responseDTOAssembler.toModel(newRequest);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
