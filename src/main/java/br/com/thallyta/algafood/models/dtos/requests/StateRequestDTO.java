package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StateRequestDTO {

    @NotBlank(message = "O nome do estado é obrigatório")
    private String name;
}
