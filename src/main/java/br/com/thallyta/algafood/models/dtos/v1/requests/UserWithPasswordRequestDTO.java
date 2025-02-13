package br.com.thallyta.algafood.models.dtos.v1.requests;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserWithPasswordRequestDTO extends UserRequestDTO{

    @NotBlank(message="O campo senha deve ser informado")
    private String password;
}
