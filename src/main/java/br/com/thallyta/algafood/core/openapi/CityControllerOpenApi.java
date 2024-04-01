package br.com.thallyta.algafood.core.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.requests.CityRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.CityResponseDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cidades")
public interface CityControllerOpenApi{

    @ApiOperation("Busca todas as cidades cadastradas")
    CollectionModel<CityResponseDTO> getAll();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade é inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Cidade não existe", response = LogExceptionAdapter.class)
    })
    CityResponseDTO getById(@ApiParam(value="ID de uma cidade", example = "1")
                                   Long id);

    @ApiOperation("Cria uma nova cidade")
    CityResponseDTO create(@ApiParam(name="corpo", value="Representação de uma nova cidade")
                                  CityRequestDTO cityRequestDTO);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponse(code = 404, message = "Cidade não encontrada", response = LogExceptionAdapter.class)
    CityResponseDTO update(@ApiParam(value="ID de uma cidade", example = "1") Long id,
                                  @ApiParam(name="corpo", value="Representação de uma nova cidade") CityRequestDTO cityRequestDTO);

    @ApiOperation("Deleta uma cidade por ID")
    @ApiResponse(code = 404, message = "Cidade não encontrada", response = LogExceptionAdapter.class)
    void delete(@ApiParam(value="ID de uma cidade", example = "1") Long id);
}
