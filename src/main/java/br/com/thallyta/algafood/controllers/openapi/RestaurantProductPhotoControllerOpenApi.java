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
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
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
            })
    })
    ProductPhotoResponseDTO findById(Long restaurantId,
                                     Long productId);

    @Operation(hidden = true)
    ResponseEntity<?> findFile(Long restaurantId,
                              Long productId,
                              @RequestHeader(name="accept") String acceptHeader)  throws HttpMediaTypeNotAcceptableException;

    ResponseEntity<Void> delete(Long restaurantId,
                                Long productId);
}
