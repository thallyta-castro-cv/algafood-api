package br.com.thallyta.algafood.core.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.requests.FormPaymentRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.FormPaymentResponseDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Api(tags= "Formas de pagamento")
public interface FormPaymentControllerOpenApi {

    @ApiOperation("Lista todas as formas de pagamento")
    public ResponseEntity<List<FormPaymentResponseDTO>> getAll(ServletWebRequest request);

    @ApiOperation("Busca uma forma de pagamento por id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = LogExceptionAdapter.class)
    })
    public ResponseEntity<FormPaymentResponseDTO> getById(@ApiParam(value = "ID de uma forma de pagamento", example = "1") Long id);

    @ApiOperation("Cadastra uma forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Forma de pagamento cadastrada"),
    })
    public FormPaymentResponseDTO create(
            @ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento")
            FormPaymentRequestDTO formPaymentRequestDTO);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada",  response = LogExceptionAdapter.class)
    })
    public FormPaymentResponseDTO update(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1")
            Long formPaymentId,
            @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados")
            FormPaymentRequestDTO formPaymentRequestDTO);

    @ApiOperation("Exclui uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento excluída"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada",  response = LogExceptionAdapter.class)
    })
    public void delete(Long formPaymentId);
}
