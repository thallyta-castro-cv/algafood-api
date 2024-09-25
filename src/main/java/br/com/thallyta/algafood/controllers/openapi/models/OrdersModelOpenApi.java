package br.com.thallyta.algafood.controllers.openapi.models;

import br.com.thallyta.algafood.models.dtos.responses.OrderSummaryResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("OrdersModel")
@Getter
@Setter
public class OrdersModelOpenApi {

    private OrdersEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PagedModelOpenApi page;

    @ApiModel("OrdersEmbeddedModel")
    @Data
    public static class OrdersEmbeddedModelOpenApi {
        private List<OrderSummaryResponseDTO> requests;
    }
}
