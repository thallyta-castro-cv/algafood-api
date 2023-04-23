package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StateRequestDTO {

    @NotBlank
    private String name;
}
