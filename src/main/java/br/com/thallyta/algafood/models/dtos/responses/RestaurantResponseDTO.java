package br.com.thallyta.algafood.models.dtos.responses;

import br.com.thallyta.algafood.models.views.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantResponseDTO {

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
