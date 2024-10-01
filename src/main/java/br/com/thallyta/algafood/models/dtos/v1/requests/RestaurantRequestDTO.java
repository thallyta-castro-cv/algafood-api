package br.com.thallyta.algafood.models.dtos.v1.requests;

import br.com.thallyta.algafood.core.validation.annotation.ShippingFee;
import br.com.thallyta.algafood.core.validation.annotation.ValueZeroIncludeDescription;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@ValueZeroIncludeDescription(fieldValue = "shippingFee", fieldDescription = "name", requireDescription = "Frete Grátis")
public class RestaurantRequestDTO {

    @NotBlank(message = "O campo nome deve ser informado")
    private String name;

    @NotNull(message = "O campo taxa frete deve ser informado")
    @PositiveOrZero(message = "O campo deve ser maior que 0")
    @ShippingFee(message = "O campo taxa frete deve ser informado")
    private BigDecimal shippingFee;

    @NotNull(message = "O campo cozinha deve ser informado")
    private KitchenIdRequestDTO kitchen;

    @Valid
    @NotNull
    private AddressRequestDTO address;
}
