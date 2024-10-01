package br.com.thallyta.algafood.controllers.v1.openapi.models;

import br.com.thallyta.algafood.models.dtos.v1.responses.FormPaymentResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("FormPaymentsModel")
@Data
public class FormPaymentsModelOpenApi {

    private FormPaymentsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("FormPaymentsEmbeddedModel")
    @Data
    public static class FormPaymentsEmbeddedModelOpenApi {
        private List<FormPaymentResponseDTO> formPayments;
    }
}
