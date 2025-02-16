package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@Relation(collectionRelation = "cities")
public class CityResponseDTO extends RepresentationModel<CityResponseDTO> {

    @Schema(example= "1")
    private Long id;

    @Schema(example= "Rio de Janeiro")
    private String name;

    @Schema(example= "1")
    private Long stateId;

    @Schema(example= "Rio de Janeiro")
    private String stateName;
}
