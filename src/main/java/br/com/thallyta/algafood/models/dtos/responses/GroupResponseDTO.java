package br.com.thallyta.algafood.models.dtos.responses;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupResponseDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Gerente")
    private String name;
}
