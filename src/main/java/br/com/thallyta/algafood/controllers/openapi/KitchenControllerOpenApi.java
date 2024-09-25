package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.requests.KitchenRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.KitchenResponseDTO;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags= "Cozinhas")
public interface KitchenControllerOpenApi {

    @ApiOperation("Lista Todas as Cozinhas")
    PagedModel<KitchenResponseDTO> getAll(Pageable pageable);


    @ApiOperation("Busca uma Cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = LogExceptionAdapter.class)
    })
    KitchenResponseDTO getById(@ApiParam(value = "ID de uma cozinha", example = "1") Long id);


    @ApiOperation("Cria uma nova Cozinha")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cozinha cadastrada"),
    })
    KitchenResponseDTO create(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha")
                              KitchenRequestDTO kitchenRequestDTO);


    @ApiOperation("Atualiza uma Cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = LogExceptionAdapter.class)
    })
    KitchenResponseDTO update(@ApiParam(value = "ID de uma cozinha", example = "1") Long id,
                              @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados") KitchenRequestDTO kitchenRequestDTO);


    @ApiOperation("Deleta uma Cozinha por ID")
    @ApiResponse(code = 404, message = "Cozinha não encontrada", response = LogExceptionAdapter.class)
    void delete(Long id);

}
