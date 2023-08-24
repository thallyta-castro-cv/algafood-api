package br.com.thallyta.algafood.models.dtos.responses;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserResponseDTO {

    @NotBlank(message="O campo nome deve ser informado")
    private String name;

    @NotBlank
    @Email(message="O campo email deve ser informado")
    private String email;
}
