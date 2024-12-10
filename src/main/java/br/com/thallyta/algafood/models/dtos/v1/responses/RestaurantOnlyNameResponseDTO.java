package br.com.thallyta.algafood.models.dtos.v1.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurants")
@Setter
@Getter
public class RestaurantOnlyNameResponseDTO extends RepresentationModel<RestaurantOnlyNameResponseDTO> {

    private Long id;
    private String name;
}
