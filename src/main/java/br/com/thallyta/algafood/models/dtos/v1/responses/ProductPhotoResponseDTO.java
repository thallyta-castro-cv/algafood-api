package br.com.thallyta.algafood.models.dtos.v1.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "photos")
@Getter
@Setter
public class ProductPhotoResponseDTO extends RepresentationModel<ProductPhotoResponseDTO> {

    private String fileName;
    private String contentType;
    private Long size;
    private String description;
}
