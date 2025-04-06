package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurants")
@Setter
@Getter
public class RestaurantOnlyNameResponseDTO extends RepresentationModel<RestaurantOnlyNameResponseDTO> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Thai Gourmet")
    private String name;
}
