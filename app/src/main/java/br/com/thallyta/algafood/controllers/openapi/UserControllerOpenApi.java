package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.requests.UserPasswordRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.requests.UserRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.requests.UserWithPasswordRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuários")
public interface UserControllerOpenApi {

    @Operation(summary = "Lista os usuários")
    CollectionModel<UserResponseDTO> getAll();

    @Operation(summary = "Busca um usuário por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) }),
    })
    UserResponseDTO getUser(@Parameter(description = "ID do usuário", example = "1", required = true) Long userId);

    @Operation(summary = "Cadastra um usuário", responses = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado"),
    })
    UserResponseDTO create(@RequestBody(description = "Representação de um novo usuário", required = true) UserWithPasswordRequestDTO userId);

    @Operation(summary = "Atualiza um usuário por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) })
    })
    UserResponseDTO update(@Parameter(description = "ID do usuário", example = "1", required = true) Long userId,
                           @RequestBody(description = "Representação de um usuário com os novos dados", required = true) UserRequestDTO userInput);

    @Operation(summary = "Atualiza a senha de um usuário", responses = {
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problem Details")) })
    })
    ResponseEntity<Void> updatePassword(@Parameter(description = "ID do usuário", example = "1", required = true) Long userId,
                                        @RequestBody(description = "Representação de uma nova senha", required = true) UserPasswordRequestDTO password);
}
