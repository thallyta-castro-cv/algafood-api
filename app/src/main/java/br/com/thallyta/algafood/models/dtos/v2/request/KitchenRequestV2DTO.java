package br.com.thallyta.algafood.models.dtos.v2.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
public class KitchenRequestV2DTO {

    @NotBlank
    private String kitchenName;
}
