package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.responses.FormPaymentResponseDTO;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestaurantFormPaymentsControllerOpenApi {

    @ApiOperation("Lista as formas de pagamento associadas a restaurante")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    List<FormPaymentResponseDTO> getAll(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                                                   Long restaurantId);

    @ApiOperation("Desassociação de restaurante com forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado",
                    response = LogExceptionAdapter.class)
    })
    void unbind(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
                       @ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long formPaymentId);

    @ApiOperation("Associação de restaurante com forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado",
                    response = LogExceptionAdapter.class)
    })
    void bind(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
              @ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long formPaymentId);
}
