package br.com.thallyta.algafood.controllers.openapi.models;

import br.com.thallyta.algafood.models.dtos.responses.UserResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("UsersModel")
@Data
public class UsersModelOpenApi {

    private UsersEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("UsersEmbeddedModel")
    @Data
    public static class UsersEmbeddedModelOpenApi {
        private List<UserResponseDTO> users;
    }
}
