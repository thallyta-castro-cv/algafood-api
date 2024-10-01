package br.com.thallyta.algafood.models.dtos.v1.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityRequestDTO {

    @NotBlank(message = "O campo nome da cidade deve ser informado")
    private String name;

    @NotNull(message = "O campo estado deve ser informado")
    private StateIdRequestDTO state;

}
