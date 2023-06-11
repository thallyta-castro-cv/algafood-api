package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CityIdRequestDTO {

    @NotNull
    private Long id;
}
