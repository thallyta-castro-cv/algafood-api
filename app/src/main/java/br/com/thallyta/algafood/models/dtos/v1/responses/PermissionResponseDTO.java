package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissions")
@Getter
@Setter
public class PermissionResponseDTO
        extends RepresentationModel<PermissionResponseDTO> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "CONSULTAR_COZINHAS")
    private String name;

    @Schema(example = "Permite consultar cozinhas")
    private String description;
}
