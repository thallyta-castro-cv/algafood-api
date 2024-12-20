package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.requests.ProductRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.ProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produtos")
public interface RestaurantProductControllerOpenApi {

    @Operation(summary = "Lista os produtos de um restaurante", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    CollectionModel<ProductResponseDTO> getAll(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId,
                                               @Parameter(description = "Incluir inativos", example = "false", required = false) Boolean includeActiveOnly);

    @Operation(summary = "Busca um produto de um restaurante", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ProductResponseDTO finById(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId,
                               @Parameter(description = "ID do produto", example = "1", required = true) Long productId);

    @Operation(summary = "Cadastra um produto de um restaurante", responses = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ProductResponseDTO save(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId,
                            @RequestBody(description = "Representação de um novo produto", required = true) ProductRequestDTO productRequest);

    @Operation(summary = "Atualiza um produto de um restaurante", responses = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado"),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ProductResponseDTO update(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurantId,
                              @Parameter(description = "ID do produto", example = "1", required = true) Long productId,
                              @RequestBody(description = "Representação de um produto com os novos dados", required = true) ProductRequestDTO productRequestDTO);

}
