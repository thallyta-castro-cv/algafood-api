package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.responses.FormPaymentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestaurantFormPaymentControllerOpenApi {

    @Operation(summary = "Lista as formas de pagamento associadas a restaurante", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    CollectionModel<FormPaymentResponseDTO> getAll(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId);


    @Operation(summary = "Associação de restaurante com forma de pagamento", responses = {
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> unbind(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId,
                                @Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formPaymentId);

    @Operation(summary = "Associação de restaurante com forma de pagamento", responses = {
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> bind(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId,
                              @Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formPaymentId);
}
