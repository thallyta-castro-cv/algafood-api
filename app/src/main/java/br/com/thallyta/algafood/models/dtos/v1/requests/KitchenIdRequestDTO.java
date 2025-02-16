package br.com.thallyta.algafood.models.dtos.v1.requests;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class KitchenIdRequestDTO {

    @NotNull(message = "O campo cozinha deve ser informado")
    Long id;
}
