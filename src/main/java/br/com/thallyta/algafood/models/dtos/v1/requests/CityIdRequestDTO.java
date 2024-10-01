package br.com.thallyta.algafood.models.dtos.v1.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityIdRequestDTO {

    @NotNull(message = "O campo cidade deve ser informado")
    private Long id;
}
