package br.com.thallyta.algafood.models.dtos.v1.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@Relation(collectionRelation = "cities")
public class CityResponseDTO extends RepresentationModel<CityResponseDTO> {

    private Long id;
    private String name;
    private Long stateId;
    private String stateName;
}
