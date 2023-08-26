package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank(message="O campo nome deve ser informado")
    private String name;

    @NotBlank
    @Email(message="O campo email deve ser informado")
    private String email;
}
