package br.com.thallyta.algafood.core.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.responses.GroupResponseDTO;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Usuários")
public interface UserGroupControllerOpenApi {

    @ApiOperation("Lista os grupos associados a um usuário")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = LogExceptionAdapter.class)
    })
    List<GroupResponseDTO> getAll(@ApiParam(value = "ID do usuário", example = "1", required = true) Long userId);

    @ApiOperation("Desassociação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
                    response = LogExceptionAdapter.class)
    })
    void unbind(@ApiParam(value = "ID do usuário", example = "1", required = true) Long userId,
                @ApiParam(value = "ID do grupo", example = "1", required = true) Long groupId);

    @ApiOperation("Associação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
                    response = LogExceptionAdapter.class)
    })
    void bind(@ApiParam(value = "ID do usuário", example = "1", required = true) Long userId,
              @ApiParam(value = "ID do grupo", example = "1", required = true) Long groupId);
}
