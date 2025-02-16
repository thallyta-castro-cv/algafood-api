package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.requests.ProductPhotoRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.ProductPhotoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produtos")
public interface RestaurantProductPhotoControllerOpenApi {

    @Operation(summary = "Atualiza a foto do produto de um restaurante")
    ProductPhotoResponseDTO updateFile(@Parameter(description = "Id do restaurante", example = "1", required = true) Long restaurantId,
                                       @Parameter(description = "Id do produto", example = "2", required = true) Long productId,
                                       @RequestBody(required = true) ProductPhotoRequestDTO productPhotoRequestDTO) throws IOException;

    @Operation(summary = "Busca a foto do produto de um restaurante", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductPhotoResponseDTO.class)),
                    @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
                    @Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
            }),
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
            @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ProductPhotoResponseDTO findById(Long restaurantId,
                                     Long productId);

    @Operation(hidden = true)
    ResponseEntity<?> findFile(Long restaurantId, Long productId, String acceptHeader)  throws HttpMediaTypeNotAcceptableException;

    @Operation(summary = "Exclui a foto do produto de um restaurante", responses = {
            @ApiResponse(responseCode = "204", description = "Foto do produto excluída"),
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
            @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    ResponseEntity<Void> delete(Long restaurantId, Long productId);
}
