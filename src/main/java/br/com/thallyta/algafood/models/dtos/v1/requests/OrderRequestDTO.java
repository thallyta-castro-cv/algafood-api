package br.com.thallyta.algafood.models.dtos.v1.requests;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {

    @Valid
    @NotNull
    private RestaurantIdRequestDTO restaurant;

    @Valid
    @NotNull
    private AddressRequestDTO address;

    @Valid
    @NotNull
    private FormPaymentIdRequestDTO formPayment;

    @Valid
    @Size(min = 1)
    @NotNull(message = "O item do pedido deve ser informado.")
    private List<OrderItemRequestDTO> items;
}
