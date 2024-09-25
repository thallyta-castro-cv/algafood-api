package br.com.thallyta.algafood.models.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Getter
@Setter
@Relation(collectionRelation = "restaurants")
public class RestaurantResponseDTO extends RepresentationModel<RestaurantResponseDTO> {

    private Long id;
    private String name;
    private BigDecimal shippingFee;
    private Long kitchenId;
    private String kitchenName;
    private Boolean active;
    private Boolean open;
    private String addressCep;
    private String addressStreet;
    private String addressNumber;
    private String addressComplement;
    private String addressNeighborhood;
    private Long addressCityId;
    private String addressCityName;
    private Long addressCityStateId;
    private String addressCityStateName;
}
