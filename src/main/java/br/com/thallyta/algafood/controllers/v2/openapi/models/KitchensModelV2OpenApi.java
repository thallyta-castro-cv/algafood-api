package br.com.thallyta.algafood.controllers.v2.openapi.models;

import br.com.thallyta.algafood.models.dtos.v2.response.KitchenResponseV2DTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("KitchensModelV2")
@Setter
@Getter
public class KitchensModelV2OpenApi {

    private KitchensEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelV2OpenApi page;

    @ApiModel("KitchensV2EmbeddedModel")
    @Data
    public static class KitchensEmbeddedModelOpenApi {
        private List<KitchenResponseV2DTO> kitchens;
    }

}
