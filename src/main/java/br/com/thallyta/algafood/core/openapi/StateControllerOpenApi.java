package br.com.thallyta.algafood.core.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.requests.StateRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.StateResponseDTO;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Estados")
public interface StateControllerOpenApi {

    @ApiOperation("Lista os estados")
    List<StateResponseDTO> getAll();

    @ApiOperation("Busca um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do estado inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = LogExceptionAdapter.class)
    })
    StateResponseDTO getById(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
            Long stateId);

    @ApiOperation("Cadastra um estado")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Estado cadastrado"),
    })
    StateResponseDTO create(
            @ApiParam(name = "corpo", value = "Representação de um novo estado", required = true)
            StateRequestDTO stateRequestDTO);

    @ApiOperation("Atualiza um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Estado atualizado"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = LogExceptionAdapter.class)
    })
    StateResponseDTO update(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
            Long stateId,

            @ApiParam(name = "corpo", value = "Representação de um estado com os novos dados", required = true)
            StateRequestDTO stateRequestDTO);

    @ApiOperation("Exclui um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado excluído"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = LogExceptionAdapter.class)
    })
    void delete(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
            Long stateId);
}
