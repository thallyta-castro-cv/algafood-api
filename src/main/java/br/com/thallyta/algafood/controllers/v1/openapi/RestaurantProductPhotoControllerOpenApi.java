package br.com.thallyta.algafood.controllers.v1.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.v1.requests.ProductPhotoRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.ProductPhotoResponseDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "Produtos")
public interface RestaurantProductPhotoControllerOpenApi {

    @ApiOperation("Atualiza a foto do produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto atualizada"),
            @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    ProductPhotoResponseDTO updateFile(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
                                       @ApiParam(value = "ID do produto", example = "1", required = true) Long productId,
                                       ProductPhotoRequestDTO productPhotoRequestDTO,
                                       @ApiParam(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)",
                                                 required = true) MultipartFile file) throws IOException;

    @ApiOperation(value = "Busca a foto do produto de um restaurante",
            produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = LogExceptionAdapter.class)
    })
    ProductPhotoResponseDTO findById(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
                                     @ApiParam(value = "ID do produto", example = "1", required = true) Long productId);

    @ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
    ResponseEntity<?> findFile(Long restaurantId, Long productId, String acceptHeader)
            throws HttpMediaTypeNotAcceptableException;

    @ApiOperation("Exclui a foto do produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Foto do produto excluída"),
            @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = LogExceptionAdapter.class)
    })
    void delete(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
                       @ApiParam(value = "ID do produto", example = "1", required = true) Long productId);
}
