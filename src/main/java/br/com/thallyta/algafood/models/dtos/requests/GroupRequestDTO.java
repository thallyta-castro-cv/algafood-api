package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GroupRequestDTO {

    @NotBlank(message = "O campo nome deve ser informado")
    private String name;
}
