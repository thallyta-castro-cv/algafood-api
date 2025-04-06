package br.com.thallyta.algafood.models.dtos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CityRequestDTO {

    @Schema(example= "Rio de Janeiro")
    @NotBlank(message = "O campo nome da cidade deve ser informado")
    private String name;

    @NotNull(message = "O campo estado deve ser informado")
    private StateIdRequestDTO state;

}
