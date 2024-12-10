package br.com.thallyta.algafood.models.dtos.v1.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Setter
@Getter
public class RestaurantBasicResponseDTO extends RepresentationModel<RestaurantBasicResponseDTO> {

    private Long id;
    private String name;
    private BigDecimal shippingFee;
    private KitchenResponseDTO kitchen;

}
