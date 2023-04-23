package br.com.thallyta.algafood.models.dtos.requests;

import br.com.thallyta.algafood.core.validation.annotation.ShippingFee;
import br.com.thallyta.algafood.core.validation.annotation.ValueZeroIncludeDescription;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@ValueZeroIncludeDescription(fieldValue = "shippingFee", fieldDescription = "name", requireDescription = "Frete Grátis")
public class RestaurantRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    @ShippingFee
    private BigDecimal shippingFee;

    @NotNull
    private KitchenIdRequestDTO kitchen;
}
