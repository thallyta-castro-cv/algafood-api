package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CityRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    private StateIdRequestDTO state;

}
