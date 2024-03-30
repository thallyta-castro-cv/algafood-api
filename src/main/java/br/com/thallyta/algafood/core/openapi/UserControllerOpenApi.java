package br.com.thallyta.algafood.core.openapi;


import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.requests.UserPasswordRequestDTO;
import br.com.thallyta.algafood.models.dtos.requests.UserRequestDTO;
import br.com.thallyta.algafood.models.dtos.requests.UserWithPasswordRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.UserResponseDTO;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Usuários")
public interface UserControllerOpenApi {

    @ApiOperation("Lista os usuários")
    List<UserResponseDTO> getAll();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do usuário inválido", response = LogExceptionAdapter.class),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = LogExceptionAdapter.class)
    })
    UserResponseDTO getUser(@ApiParam(value = "ID do usuário", example = "1", required = true) Long userId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrado"),
    })
    UserResponseDTO create(@ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true)
                           UserWithPasswordRequestDTO userId);

    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = LogExceptionAdapter.class)
    })
    UserResponseDTO update(@ApiParam(value = "ID do usuário", example = "1", required = true) Long userId,
                           @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados", required = true) UserRequestDTO userInput);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = LogExceptionAdapter.class)
    })
    void updatePassword(@ApiParam(value = "ID do usuário", example = "1", required = true) Long userId,
                        @ApiParam(name = "corpo", value = "Representação de uma nova senha",
                                  required = true) UserPasswordRequestDTO password);
}
