package br.com.thallyta.algafood.models.dtos.v1.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank(message = "O nome do produto deve ser informado.")
    private String name;

    @NotBlank(message = "A descrição do produto deve ser informada.")
    private String description;

    @NotNull(message = "O oreço do produto deve ser informado.")
    @PositiveOrZero(message = "O preço do produto deve ser 0 ou positivo")
    private BigDecimal price;

    @NotNull(message = "O campo ativo deve ser informado.")
    private Boolean active;
}
