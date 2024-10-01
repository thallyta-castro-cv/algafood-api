package br.com.thallyta.algafood.controllers.v1.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.v1.responses.GroupResponseDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Usuários")
public interface UserGroupControllerOpenApi {

    @ApiOperation("Lista os grupos associados a um usuário")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = LogExceptionAdapter.class)
    })
    CollectionModel<GroupResponseDTO> getAll(@ApiParam(value = "ID do usuário", example = "1", required = true) Long userId);

    @ApiOperation("Desassociação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
                    response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> unbind(@ApiParam(value = "ID do usuário", example = "1", required = true) Long userId,
                                @ApiParam(value = "ID do grupo", example = "1", required = true) Long groupId);

    @ApiOperation("Associação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
                    response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> bind(@ApiParam(value = "ID do usuário", example = "1", required = true) Long userId,
              @ApiParam(value = "ID do grupo", example = "1", required = true) Long groupId);
}
