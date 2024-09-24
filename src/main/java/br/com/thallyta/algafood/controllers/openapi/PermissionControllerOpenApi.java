package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.responses.PermissionResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permissions")
public interface PermissionControllerOpenApi {

    @ApiOperation("Lista as permissões")
    CollectionModel<PermissionResponseDTO> getAll();
}
