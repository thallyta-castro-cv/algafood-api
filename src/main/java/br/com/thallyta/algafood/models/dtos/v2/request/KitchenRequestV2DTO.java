package br.com.thallyta.algafood.models.dtos.v2.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class KitchenRequestV2DTO {

    @NotBlank
    private String kitchenName;
}
