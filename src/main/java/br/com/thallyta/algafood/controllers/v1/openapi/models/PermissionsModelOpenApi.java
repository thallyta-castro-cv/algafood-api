package br.com.thallyta.algafood.controllers.v1.openapi.models;

import br.com.thallyta.algafood.models.dtos.v1.responses.PermissionResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PermissionsModel")
@Data
public class PermissionsModelOpenApi {

    private PermissionsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("PermissionsEmbeddedModel")
    @Data
    public static class PermissionsEmbeddedModelOpenApi {
        private List<PermissionResponseDTO> permissions;
    }
}
