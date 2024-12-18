package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.requests.CityRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.CityResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CityControllerOpenApi{

    @Operation(summary = "Lista as cidades")
    CollectionModel<CityResponseDTO> getAll();

    @Operation(summary = "Busca uma cidade por id", responses = {
        @ApiResponse(responseCode= "200"),
        @ApiResponse(responseCode= "400",
                     description= "ID da cidade inválido",
                     content = @Content(schema = @Schema(ref = "Problem Details")))
    })
    CityResponseDTO getById(@Parameter(description = "ID da cidade", example = "1", required = true) Long id);

    @Operation(summary = "Cria uma cidade", description = "Cadastro de uma cidade, necessita de um estado e um nome válido")
    CityResponseDTO create(@RequestBody(description = "Representação de uma nova cidade") CityRequestDTO cityRequestDTO);

    @Operation(summary = "Atualiza uma cidade")
    CityResponseDTO update(@Parameter(description = "ID da cidade", example = "1", required = true)Long id,
                           @RequestBody(description = "Representação de uma cidade com dados atualizados" ) CityRequestDTO cityRequestDTO);

    @Operation(summary = "Deleta uma cidade")
    void delete(@Parameter(description = "ID da cidade", example = "1", required = true) Long id);
}
