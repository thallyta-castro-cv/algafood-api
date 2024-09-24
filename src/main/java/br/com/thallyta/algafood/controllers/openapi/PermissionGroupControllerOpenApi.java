package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.responses.PermissionResponseDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Grupos")
public interface PermissionGroupControllerOpenApi {

    @ApiOperation("Lista as permissões associadas a um grupo")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = LogExceptionAdapter.class)
    })
    CollectionModel<PermissionResponseDTO> getAll(@ApiParam(value = "ID do grupo", example = "1", required = true)
                                       Long groupId);

    @ApiOperation("Desassociação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada",
                    response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> unbind(@ApiParam(value = "ID do grupo", example = "1", required = true) Long groupId,
                                @ApiParam(value = "ID da permissão", example = "1", required = true) Long permissionId);

    @ApiOperation("Associação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada",
                    response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> bind(@ApiParam(value = "ID do grupo", example = "1", required = true) Long groupId,
              @ApiParam(value = "ID da permissão", example = "1", required = true) Long permissionId);
}
