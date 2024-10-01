package br.com.thallyta.algafood.controllers.v1.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.v1.requests.RestaurantRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantBasicResponseDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantResponseDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Restaurantes")
public interface RestaurantControllerOpenApi {

    @ApiOperation(value = "Lista todos os restaurantes", response = RestaurantBasicResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
                    name = "projecao", paramType = "query", type = "string")
    })
    CollectionModel<RestaurantResponseDTO> getAll();

    @ApiOperation("Busca um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    RestaurantResponseDTO getById(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id);

    @ApiOperation("Cadastra um restaurante")
    @ApiResponses({@ApiResponse(code = 201, message = "Restaurante cadastrado")})
    RestaurantResponseDTO create(@ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true)
                                 RestaurantRequestDTO restaurantRequestDTO);

    @ApiOperation("Atualiza um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante atualizado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    RestaurantResponseDTO update(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id,
                                 @ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados", required = true)
                                 @RequestBody @Valid RestaurantRequestDTO restaurantRequestDTO);

    public void delete(@PathVariable Long id);

    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> active(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id);

    @ApiOperation("Inativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> inactive(@PathVariable Long id);

    @ApiOperation("Abre um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> open(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restaurantId);

    @ApiOperation("Fecha um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = LogExceptionAdapter.class)
    })
    ResponseEntity<Void> close( @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restaurantId);

    @ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
    void activeSeveral(@RequestBody List<Long> restaurantsId);

    @ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
    void inactiveSeveral(@RequestBody List<Long> restaurantsId);
}
