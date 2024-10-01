package br.com.thallyta.algafood.controllers.v2.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.v2.request.KitchenRequestV2DTO;
import br.com.thallyta.algafood.models.dtos.v2.response.KitchenResponseV2DTO;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Cozinhas")
public interface KitchenControllerV2OpenApi {

    @ApiOperation("Lista as cozinhas com paginação")
    PagedModel<KitchenResponseV2DTO> list(Pageable pageable);

    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = LogExceptionAdapter.class)
    })
    KitchenResponseV2DTO getById(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true)
            Long kitchenId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cozinha cadastrada"),
    })
    KitchenResponseV2DTO add(
            @ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true)
            KitchenRequestV2DTO kitchenRequestDTO);

    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = LogExceptionAdapter.class)
    })
    KitchenResponseV2DTO update(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true)
            Long kitchenId,

            @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados",
                    required = true)
            KitchenRequestV2DTO kitchenRequestDTO);

    @ApiOperation("Exclui uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha excluída"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = LogExceptionAdapter.class)
    })
    void remove(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true)
            Long kitchenId);
}
