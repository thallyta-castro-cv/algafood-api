package br.com.thallyta.algafood.models.dtos.v1.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserPasswordRequestDTO {

    @NotBlank(message = "Informe a senha atual")
    private String currentPassword;

    @NotBlank(message = "A nova senha deve ser informada")
    private String newPassword;
}
