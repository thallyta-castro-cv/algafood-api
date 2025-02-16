package br.com.thallyta.algafood.models.dtos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequestDTO {

    @Schema(example = "João da Silva")
    @NotBlank(message="O campo nome deve ser informado")
    private String name;

    @Schema(example = "joao.ger@algafood.com.br")
    @NotBlank
    @Email(message="O campo email deve ser informado")
    private String email;
}
