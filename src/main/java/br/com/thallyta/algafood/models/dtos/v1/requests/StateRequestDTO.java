package br.com.thallyta.algafood.models.dtos.v1.requests;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class StateRequestDTO {

    @NotBlank(message = "O nome do estado é obrigatório")
    private String name;
}
