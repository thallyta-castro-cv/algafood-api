package br.com.thallyta.algafood.controllers.openapi.models;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Links")
public class LinksModelOpenApi {
    private LinkModel rel;

    @Setter
    @Getter
    @ApiModel("Link")
    private static class LinkModel {
        private String href;
        private boolean templated;
    }
}
