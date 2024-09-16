package br.com.thallyta.algafood.models.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "formPayments")
@Getter
@Setter
public class FormPaymentResponseDTO extends RepresentationModel<FormPaymentResponseDTO> {

    private Long id;
    private String description;
}
