package br.com.thallyta.algafood.controllers.v1.openapi;

import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.v1.requests.GroupRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.GroupResponseDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Grupos")
public interface GroupControllerOpenApi {


    @ApiOperation("Lista todos os grupos")
    CollectionModel<GroupResponseDTO> getAll();

    @ApiOperation("Busca um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = LogExceptionAdapter.class)
    })
    GroupResponseDTO getById(@ApiParam(value = "ID de um grupo", example = "1") Long id);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado"),
    })
    GroupResponseDTO create(@ApiParam(name = "corpo", value = "Representação de um novo grupo")
                            GroupRequestDTO groupRequestDTO);

    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = LogExceptionAdapter.class)
    })
    GroupResponseDTO update(@ApiParam(value = "ID de um grupo", example = "1") Long id,
                            @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados")
                            GroupRequestDTO groupRequestDTO);

    @ApiOperation("Exclui um grupo por ID")
    @ApiResponses({@ApiResponse(code = 404, message = "Grupo não encontrado",
            response = LogExceptionAdapter.class)})
    void delete(@ApiParam(value = "ID de um grupo", example = "1") Long id);

}
