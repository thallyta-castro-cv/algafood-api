package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class RequestItemRequestDTO {

    @NotNull(message = "O campo produto deve ser informado.")
    private Long productId;

    @NotNull(message = "O campo quantidade deve ser informado.")
    @PositiveOrZero(message = "O campo quantidade deve conter um valor positivo.")
    private Integer amount;

    private String note;
}
