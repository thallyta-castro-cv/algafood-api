package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KitchenIdRequestDTO {

    @NotNull(message = "O campo cozinha deve ser informado")
    Long id;
}
