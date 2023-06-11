package br.com.thallyta.algafood.models.dtos.responses;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RestaurantResponseDTO {

    private Long id;
    private String name;
    private BigDecimal shippingFee;
    private Long kitchenId;
    private String kitchenName;
    private Boolean active;
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
