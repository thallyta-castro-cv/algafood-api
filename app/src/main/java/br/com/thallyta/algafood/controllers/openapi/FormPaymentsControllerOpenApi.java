package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.requests.FormPaymentRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.FormPaymentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Formas de pagamento")
public interface FormPaymentsControllerOpenApi {

    @Operation(summary = "Lista as formas de pagamento")
    ResponseEntity<CollectionModel<FormPaymentResponseDTO>> getAll(@Parameter(hidden = true) ServletWebRequest request);

    @Operation(summary = "Busca uma forma de pagamento por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) })
    })
    ResponseEntity<FormPaymentResponseDTO> getById(@Parameter(description = "ID de uma forma de pagamento", example = "1", required = true) Long id);

    @Operation(summary = "Cadastra uma forma de pagamento", responses = {
            @ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada")})
    FormPaymentResponseDTO create(@RequestBody(description = "Representação de uma nova forma de pagamento", required = true) FormPaymentRequestDTO formPaymentRequestDTO);

    @Operation(summary = "Atualiza uma forma de pagamento por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada"),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) })
    })
    FormPaymentResponseDTO update(@Parameter(description = "ID de uma forma de pagamento", example = "1", required = true) Long id,
                                  @RequestBody(description = "Representação de uma forma de pagamento com os novos dados", required = true) FormPaymentRequestDTO formPaymentRequestDTO);

    @Operation(summary = "Exclui uma forma de pagamento por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Forma de pagamento excluída"),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) })
    })
    ResponseEntity<Void> delete(@Parameter(description = "ID de uma forma de pagamento", example = "1", required = true) Long id);
}
