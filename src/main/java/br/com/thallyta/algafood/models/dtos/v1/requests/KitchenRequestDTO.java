package br.com.thallyta.algafood.models.dtos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class KitchenRequestDTO {

    @Schema(example = "Brasileira")
    @NotBlank(message = "O campo nome deve ser informado")
    private String name;
}
