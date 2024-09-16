package br.com.thallyta.algafood.controllers.openapi.models;

import br.com.thallyta.algafood.models.dtos.responses.KitchenResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("KitchensModel")
@Getter
@Setter
public class KitchensModelOpenApi extends PageModelOpenApi<KitchenResponseDTO>{}
