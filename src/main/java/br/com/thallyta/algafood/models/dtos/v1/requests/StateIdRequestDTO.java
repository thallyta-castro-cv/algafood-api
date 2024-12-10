package br.com.thallyta.algafood.models.dtos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StateIdRequestDTO {

    @Schema(example= "1")
    @NotNull(message = "Estado é obrigatório")
    private Long id;
}
