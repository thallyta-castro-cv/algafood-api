package br.com.thallyta.algafood.controllers.v1.openapi.models;

import br.com.thallyta.algafood.models.dtos.v1.responses.CityResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CitiesModel")
@Data
public class CitiesModelOpenApi {

    private  CitiesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("CitiesEmbeddedModel")
    @Data
    public static class CitiesEmbeddedModelOpenApi {
        private List<CityResponseDTO> cities;
    }
}
