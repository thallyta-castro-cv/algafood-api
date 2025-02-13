package br.com.thallyta.algafood.models.dtos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class OrderItemRequestDTO {

    @Schema(example = "1")
    @NotNull(message = "O campo produto deve ser informado.")
    private Long productId;

    @Schema(example = "2")
    @NotNull(message = "O campo quantidade deve ser informado.")
    @PositiveOrZero(message = "O campo quantidade deve conter um valor positivo.")
    @Min(1)
    private Integer amount;

    @Schema(example = "Menos picante, por favor")
    private String note;
}
