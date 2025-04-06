package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.requests.RestaurantRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestaurantControllerOpenApi {

    @Operation(summary = "Lista restaurantes", parameters = {
            @Parameter(name = "projecao",
                    description = "Nome da projeção",
                    example = "apenas-nome",
                    in = ParameterIn.QUERY,
                    required = false
            )
    })
    CollectionModel<RestaurantResponseDTO> getAll();

    @Operation(summary = "Busca um restaurante por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    RestaurantResponseDTO getById(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);

    @Operation(summary = "Cadastra um restaurante", responses = {
            @ApiResponse(responseCode = "201", description = "Restaurante cadastrado"),
    })
    RestaurantResponseDTO create(@RequestBody(description = "Representação de um novo restaurante", required = true)RestaurantRequestDTO restaurantRequestDTO);

    @Operation(summary = "Atualiza um restaurante por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    RestaurantResponseDTO update(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id,
                                 @RequestBody(description = "Representação de um restaurante com os novos dados", required = true) RestaurantRequestDTO restaurantRequestDTO);

    @Operation(summary = "Ativa um restaurante por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> delete(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);

    ResponseEntity<Void> active(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);

    @Operation(summary = "Desativa um restaurante por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurante inativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> inactive(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);

    @Operation(summary = "Abre um restaurante por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> open(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Fecha um restaurante por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurante fechado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> close(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Ativa múltiplos restaurantes", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso"),
    })
    ResponseEntity<Void> activeSeveral(@RequestBody(description = "IDs de restaurantes", required = true) List<Long> restaurantsId);

    @Operation(summary = "Inativa múltiplos restaurantes", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso"),
    })
    ResponseEntity<Void> inactiveSeveral(@RequestBody(description = "IDs de restaurantes", required = true) List<Long> restaurantsId);
}
