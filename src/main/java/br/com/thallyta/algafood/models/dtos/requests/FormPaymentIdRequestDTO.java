package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormPaymentIdRequestDTO {

    @NotNull(message="O campo forma de pagamento deve ser informado.")
    private Long id;
}
