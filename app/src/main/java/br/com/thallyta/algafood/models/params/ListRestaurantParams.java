package br.com.thallyta.algafood.models.params;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ListRestaurantParams {

    private BigDecimal shippingFeeInitial;
    private BigDecimal shippingFeeFinal;
    private String name;
}
