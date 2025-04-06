package br.com.thallyta.algafood.models.dtos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class FormPaymentRequestDTO {

    @Schema(example = "Cartão de crédito")
    @NotBlank(message = "O descrição deve ser informado")
    private String description;
}
