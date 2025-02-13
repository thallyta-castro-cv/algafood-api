package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.models.dtos.v1.requests.GroupRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.GroupResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import jakarta.validation.Valid;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GroupControllerOpenApi {

    @Operation(summary = "Lista os grupos")
    CollectionModel<GroupResponseDTO> getAll();

    @Operation(summary = "Busca um grupo por ID")
    GroupResponseDTO getById(@Parameter(description = "ID de um grupo", example = "1", required = true) @PathVariable Long id);

    @Operation(summary = "Cadastra um grupo")
    GroupResponseDTO create(@RequestBody(description = "Representação de um novo grupo", required = true) @Valid GroupRequestDTO groupRequestDTO);

    @Operation(summary = "Atualiza um grupo por ID")
    GroupResponseDTO update(@Parameter(description = "ID de um grupo", example = "1", required = true) Long id,
                            @RequestBody(description = "Representação de um grupo com os novos dados", required = true) GroupRequestDTO groupRequestDTO);

    @Operation(summary = "Exclui um grupo por ID")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
