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
}
