package br.com.thallyta.algafood.controllers.v1.openapi.models;

import br.com.thallyta.algafood.models.dtos.v1.responses.StateResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("StatesModel")
@Data
public class StatesModelOpenApi {

    private StatesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("StatesEmbeddedModel")
    @Data
    public static class StatesEmbeddedModelOpenApi {
        private List<StateResponseDTO> states;
    }
}
