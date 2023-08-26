package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StateIdRequestDTO {

    @NotNull(message = "Estado é obrigatório")
    private Long id;
}
