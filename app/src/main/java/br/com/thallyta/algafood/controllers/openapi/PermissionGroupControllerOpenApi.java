package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.responses.PermissionResponseDTO;
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
@Tag(name = "Grupos")
public interface PermissionGroupControllerOpenApi {

    @Operation(summary = "Lista as permissões associadas a um grupo", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    CollectionModel<PermissionResponseDTO> getAll(@Parameter(description = "ID de um grupo", example = "1", required = true) Long groupId);


    @Operation(summary = "Desassociação de permissão com grupo", responses = {
            @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> unbind(@Parameter(description = "ID de um grupo", example = "1", required = true) Long groupId,
                                @Parameter(description = "ID de uma permissão", example = "1", required = true) Long permissionId);

    @Operation(summary = "Associação de permissão com grupo", responses = {
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> bind(@Parameter(description = "ID de um grupo", example = "1", required = true) Long groupId,
                              @Parameter(description = "ID de uma permissão", example = "1", required = true) Long permissionId);
}
