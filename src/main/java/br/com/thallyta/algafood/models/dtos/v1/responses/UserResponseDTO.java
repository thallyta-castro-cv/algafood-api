package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@Relation(collectionRelation = "users")
public class UserResponseDTO extends RepresentationModel<UserResponseDTO> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "João da Silva")
    private String name;

    @Schema(example = "joao.ger@algafood.com.br")
    private String email;
}
