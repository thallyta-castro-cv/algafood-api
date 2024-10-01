package br.com.thallyta.algafood.models.dtos.v1.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "products")
@Getter
@Setter
public class ProductResponseDTO extends RepresentationModel<ProductResponseDTO> {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
}
