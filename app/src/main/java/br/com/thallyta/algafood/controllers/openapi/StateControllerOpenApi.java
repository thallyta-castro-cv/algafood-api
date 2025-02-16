package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.requests.StateRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.StateResponseDTO;
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

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estados")
public interface StateControllerOpenApi {

    @Operation(summary = "Lista os estados")
    CollectionModel<StateResponseDTO> findAll();

    @Operation(summary = "Busca um estado por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do estado inválido", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) })
    })
    StateResponseDTO getById(@Parameter(description = "ID de um estado", example = "1", required = true) Long id);

    @Operation(summary = "Cadastra um estado", responses = {
            @ApiResponse(responseCode = "201", description = "Estado cadastrado")
    })
    StateResponseDTO create(@RequestBody(description = "Representação de um novo estado", required = true) StateRequestDTO stateRequestDTO);

    @Operation(summary = "Atualiza um estado por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Estado atualizado"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) })
    })
    StateResponseDTO update(@Parameter(description = "ID de um estado", example = "1", required = true) Long id,
                            @RequestBody(description = "Representação de um estado com os novos dados", required = true) StateRequestDTO stateRequestDTO);

    @Operation(summary = "Exclui um estado por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Estado excluído"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) })
    })
    ResponseEntity<Void> delete(@Parameter(description = "ID de um estado", example = "1", required = true) Long id);
}
