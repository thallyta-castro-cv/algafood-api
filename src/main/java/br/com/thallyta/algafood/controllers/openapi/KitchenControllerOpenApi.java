package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.core.springdoc.PageableParameter;
import br.com.thallyta.algafood.models.dtos.v1.requests.KitchenRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.KitchenResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas")
public interface KitchenControllerOpenApi {

    @PageableParameter
    @Operation(summary = "Lista as cozinhas com paginação")
    PagedModel<KitchenResponseDTO> getAll(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca uma cozinha por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido",
                    content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema")))
    })
    KitchenResponseDTO getById(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long id);

    @Operation(summary = "Cadastra uma cozinha", responses = {
            @ApiResponse(responseCode = "201", description = "Cozinha cadastrada"),
    })
    KitchenResponseDTO create(@RequestBody(description = "Representação de uma nova cozinha", required = true) KitchenRequestDTO kitchenRequestDTO);

    @Operation(summary = "Atualiza uma cozinha por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema"))),
    })
    KitchenResponseDTO update(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long id,
                              @RequestBody(description = "Representação de uma cozinha com os novos dados", required = true) KitchenRequestDTO kitchenRequestDTO);

    @Operation(summary = "Exclui uma cozinha por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Cozinha excluída"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema")))
    })
    ResponseEntity<Void> delete(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long id);
}
