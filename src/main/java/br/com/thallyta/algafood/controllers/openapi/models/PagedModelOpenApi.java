package br.com.thallyta.algafood.controllers.openapi.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PagedModel")
@Getter
@Setter
public class PagedModelOpenApi {

    @ApiModelProperty(example = "10", value = "Quantidade de registros por página")
    private Long size;

    @ApiModelProperty(example = "50", value = "Total de registros")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Total de páginas")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
    private Long number;

}
