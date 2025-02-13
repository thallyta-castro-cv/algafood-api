package br.com.thallyta.algafood.models.dtos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class AddressRequestDTO {

    @Schema(example = "38400-000")
    @NotBlank(message = "O campo cep deve ser informado")
    private String cep;

    @Schema(example = "Rua Floriano Peixoto")
    @NotBlank(message = "O campo rua deve ser informado")
    private String street;

    @Schema(example = "\"1500\"")
    @NotBlank(message = "O campo número deve ser informado")
    private String number;

    @Schema(example = "Apto 901")
    private String complement;

    @Schema(example = "Centro")
    @NotBlank(message = "O campo bairro deve ser informado")
    private String neighborhood;

    @Valid
    @NotNull(message = "O campo cidade deve ser informado")
    private CityIdRequestDTO city;
}
