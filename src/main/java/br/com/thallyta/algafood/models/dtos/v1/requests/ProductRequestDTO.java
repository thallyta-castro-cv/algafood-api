package br.com.thallyta.algafood.models.dtos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {

    @Schema(example = "Espetinho de Cupim")
    @NotBlank(message = "O nome do produto deve ser informado.")
    private String name;

    @Schema(example = "Acompanha farinha, mandioca e vinagrete")
    @NotBlank(message = "A descrição do produto deve ser informada.")
    private String description;

    @Schema(example = "12.50")
    @NotNull(message = "O oreço do produto deve ser informado.")
    @PositiveOrZero(message = "O preço do produto deve ser 0 ou positivo")
    private BigDecimal price;

    @Schema(example = "true")
    @NotNull(message = "O campo ativo deve ser informado.")
    private Boolean active;
}
