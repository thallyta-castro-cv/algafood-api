package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "photos")
@Getter
@Setter
public class ProductPhotoResponseDTO extends RepresentationModel<ProductPhotoResponseDTO> {

    @Schema(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Prime-Rib.jpg")
    private String fileName;

    @Schema(example = "image/jpeg")
    private String contentType;

    @Schema(example = "202912")
    private Long size;

    @Schema(example = "Prime Rib ao ponto")
    private String description;
}
