package br.com.thallyta.algafood.controllers.openapi.models;

import br.com.thallyta.algafood.models.dtos.responses.KitchenResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("KitchensModel")
@Getter
@Setter
public class KitchensModelOpenApi{

    private KitchensEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PagedModelOpenApi page;

    @ApiModel("KitchensEmbeddedModel")
    @Data
    public static class KitchensEmbeddedModelOpenApi {
        private List<KitchenResponseDTO> kitchens;
    }
}
