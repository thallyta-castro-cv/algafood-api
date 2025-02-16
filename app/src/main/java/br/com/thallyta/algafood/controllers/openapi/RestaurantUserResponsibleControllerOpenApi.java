package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.responses.UserResponseDTO;
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
public interface RestaurantUserResponsibleControllerOpenApi {

    @Operation(summary = "Lista os usuários responsáveis associados a restaurante", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
                    content = {@Content(schema = @Schema(ref = "Problem Details")) }),
    })
    CollectionModel<UserResponseDTO> getAll(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Desassociação de restaurante com usuário responsável", responses = {
            @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado",
                    content = {@Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> unbind(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId,
                                Long userId);

    @Operation(summary = "Associação de restaurante com usuário responsável", responses = {
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado",
                    content = {@Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> bind(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId,
                              @Parameter(description = "ID do usuário", example = "1", required = true) Long userId);
}
