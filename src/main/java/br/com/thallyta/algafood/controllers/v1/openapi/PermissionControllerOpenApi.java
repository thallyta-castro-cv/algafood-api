package br.com.thallyta.algafood.controllers.v1.openapi;

import br.com.thallyta.algafood.models.dtos.v1.responses.PermissionResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permissões")
public interface PermissionControllerOpenApi {

    @ApiOperation("Lista as permissões")
    CollectionModel<PermissionResponseDTO> getAll();
}
