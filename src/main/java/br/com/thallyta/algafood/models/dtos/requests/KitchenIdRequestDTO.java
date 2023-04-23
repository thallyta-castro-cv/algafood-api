package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class KitchenIdRequestDTO {

    @NotNull
    private Long id;
}
