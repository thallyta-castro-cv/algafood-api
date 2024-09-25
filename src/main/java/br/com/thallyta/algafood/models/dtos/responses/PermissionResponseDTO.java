package br.com.thallyta.algafood.models.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissions")
@Getter
@Setter
public class PermissionResponseDTO
        extends RepresentationModel<PermissionResponseDTO> {

    private Long id;
    private String name;
    private String description;
}
