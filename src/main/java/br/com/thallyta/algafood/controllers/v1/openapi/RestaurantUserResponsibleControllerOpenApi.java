package br.com.thallyta.algafood.controllers.v1.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.v1.responses.UserResponseDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurantes")
public interface RestaurantUserResponsibleControllerOpenApi {

    @ApiOperation("Lista os usuários responsáveis associados a restaurante")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    CollectionModel<UserResponseDTO> getAll(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId);


    @ApiOperation("Desassociação de restaurante com usuário responsável")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado",
                    response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> unbind(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
                @ApiParam(value = "ID do usuário", example = "1", required = true) Long userId);

    @ApiOperation("Associação de restaurante com usuário responsável")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado",
                    response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> bind(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restaurantId,
              @ApiParam(value = "ID do usuário", example = "1", required = true) Long userId);
}
