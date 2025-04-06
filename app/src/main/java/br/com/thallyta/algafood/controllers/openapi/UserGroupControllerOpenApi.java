package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.responses.GroupResponseDTO;
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
@Tag(name = "Usuários")
public interface UserGroupControllerOpenApi {

    @Operation(summary = "Lista os grupos associados a um usuário", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    CollectionModel<GroupResponseDTO> getAll(@Parameter(description = "ID do usuário", example = "1", required = true) Long userId);


    @Operation(summary = "Desassociação de grupo com usuário", responses = {
            @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ResponseEntity<Void> unbind(@Parameter(description = "ID do usuário", example = "1", required = true) Long userId,
                                @Parameter(description = "ID do grupo", example = "1", required = true) Long groupId);

    @Operation(summary = "Associação de grupo com usuário", responses = {
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> bind(@Parameter(description = "ID do usuário", example = "1", required = true) Long userId,
                              @Parameter(description = "ID do grupo", example = "1", required = true) Long groupId);
}
