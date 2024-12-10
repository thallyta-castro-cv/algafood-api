package br.com.thallyta.algafood.models.dtos.v2.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "kitchens")
@Setter
@Getter
public class KitchenResponseV2DTO extends RepresentationModel<KitchenResponseV2DTO> {

    private Long kitchenId;
    private String kitchenName;
}
