package br.com.thallyta.algafood.controllers.v1.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.v1.requests.ProductRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.ProductResponseDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Produtos")
public interface RestaurantProductControllerOpenApi {

    @ApiOperation("Lista os produtos de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    CollectionModel<ProductResponseDTO> getAll(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
                                               @ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem",
                                                   example = "false", defaultValue = "false") Boolean includeActiveOnly);

    @ApiOperation("Cadastra um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto cadastrado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    ProductResponseDTO save(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
                            @ApiParam(name = "corpo", value = "Representação de um novo produto", required = true) ProductRequestDTO productRequest);

    @ApiOperation("Atualiza um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    ProductResponseDTO update(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
                              @ApiParam(value = "ID do produto", example = "1", required = true) Long productId,
                              @ApiParam(name = "corpo", value = "Representação de um produto com os novos dados",
                                      required = true)ProductRequestDTO productRequestDTO);
}
