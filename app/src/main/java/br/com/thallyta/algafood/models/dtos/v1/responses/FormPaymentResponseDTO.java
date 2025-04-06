package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "formPayments")
@Getter
@Setter
public class FormPaymentResponseDTO extends RepresentationModel<FormPaymentResponseDTO> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Cartão de crédito")
    private String description;
}
