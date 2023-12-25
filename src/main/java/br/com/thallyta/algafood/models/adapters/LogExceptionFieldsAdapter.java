package br.com.thallyta.algafood.models.adapters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("Exception Adapter Response Fields to bean Validation")
public class LogExceptionFieldsAdapter {

    @ApiModelProperty(example = "price")
    private String name;

    @ApiModelProperty(example = "Campo preço é obrigatório")
    private String userMessage;
}
