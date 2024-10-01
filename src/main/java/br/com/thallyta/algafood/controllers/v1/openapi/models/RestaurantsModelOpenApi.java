package br.com.thallyta.algafood.controllers.v1.openapi.models;

import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("RestaurantsBasicModel")
@Data
public class RestaurantsModelOpenApi {

    private RestaurantsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("RestaurantsEmbeddedModel")
    @Data
    public static class RestaurantsEmbeddedModelOpenApi {
        private List<RestaurantResponseDTO> restaurants;
    }
}
