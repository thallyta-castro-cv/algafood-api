package br.com.thallyta.algafood.models.dtos.v2.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class KitchenRequestV2DTO {

    @ApiModelProperty(example = "Brasileira", required = true)
    @NotBlank
    private String kitchenName;
}
