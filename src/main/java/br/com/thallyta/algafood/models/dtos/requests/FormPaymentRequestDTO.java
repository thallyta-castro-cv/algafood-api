package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormPaymentRequestDTO {

    @NotBlank(message = "O descrição deve ser informado")
    private String description;
}
