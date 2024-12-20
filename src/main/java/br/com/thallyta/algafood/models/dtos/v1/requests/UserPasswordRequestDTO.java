package br.com.thallyta.algafood.models.dtos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserPasswordRequestDTO {

    @Schema(example = "123", type = "string")
    @NotBlank(message = "Informe a senha atual")
    private String currentPassword;

    @Schema(example = "123", type = "string")
    @NotBlank(message = "A nova senha deve ser informada")
    private String newPassword;
}
