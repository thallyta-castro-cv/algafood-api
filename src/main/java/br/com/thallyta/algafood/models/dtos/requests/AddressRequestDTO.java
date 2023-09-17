package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressRequestDTO {

    @NotBlank(message = "O campo cep deve ser informado")
    private String cep;

    @NotBlank(message = "O campo rua deve ser informado")
    private String street;

    @NotBlank(message = "O campo número deve ser informado")
    private String number;

    private String complement;

    @NotBlank(message = "O campo bairro deve ser informado")
    private String neighborhood;

    @Valid
    @NotNull(message = "O campo cidade deve ser informado")
    private CityIdRequestDTO city;
}
