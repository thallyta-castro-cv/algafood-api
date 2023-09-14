package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestaurantIdRequestDTO {

    @NotNull(message="O campo restaurante deve ser informado.")
    private Long id;
}
