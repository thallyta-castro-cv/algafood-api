package br.com.thallyta.algafood.core.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import io.swagger.annotations.*;

@Api(tags = "Pedidos")
public interface ChangeStatusRequestControllerOpenApi {

    @ApiOperation("Confirmação de pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido confirmado com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = LogExceptionAdapter.class)
    })
    void confirmRequest(@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
            required = true) String code);

    @ApiOperation("Cancelamento de pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido cancelado com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = LogExceptionAdapter.class)
    })
    void deliverRequest(@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
            required = true) String code);

    @ApiOperation("Registrar entrega de pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Entrega de pedido registrada com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = LogExceptionAdapter.class)
    })
    void cancelRequest(@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
            required = true) String code);
}
