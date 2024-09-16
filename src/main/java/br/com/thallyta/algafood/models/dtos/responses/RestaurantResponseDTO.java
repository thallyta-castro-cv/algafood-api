package br.com.thallyta.algafood.models.dtos.responses;

import br.com.thallyta.algafood.models.views.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Getter
@Setter
@Relation(collectionRelation = "restaurants")
public class RestaurantResponseDTO extends RepresentationModel<RestaurantResponseDTO> {

    @JsonView(RestaurantView.ResumeRestaurant.class)
    private Long id;

    @JsonView(RestaurantView.ResumeRestaurant.class)
    private String name;

    @JsonView(RestaurantView.ResumeRestaurant.class)
    private BigDecimal shippingFee;

    @JsonView(RestaurantView.ResumeRestaurant.class)
    private Long kitchenId;

    @JsonView(RestaurantView.ResumeRestaurant.class)
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
