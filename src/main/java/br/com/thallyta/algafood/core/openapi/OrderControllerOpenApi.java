package br.com.thallyta.algafood.core.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.requests.OrderRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.OrderResponseDTO;
import br.com.thallyta.algafood.models.dtos.responses.OrderSummaryResponseDTO;
import br.com.thallyta.algafood.models.params.ListRequestParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api(tags = "Pedidos")
public interface OrderControllerOpenApi {

    @ApiOperation("Pesquisa os pedidos")
    PagedModel<OrderSummaryResponseDTO> getAll(ListRequestParams params,
                                               @PageableDefault(size = 10) Pageable pageable);

    @ApiOperation("Busca um pedido por código")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = LogExceptionAdapter.class)
    })
    OrderResponseDTO getById(@PathVariable String code);

    @ApiOperation("Registra um pedido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido registrado"),
    })
    OrderResponseDTO createRequest(@Valid @RequestBody OrderRequestDTO requestDTO);
}
