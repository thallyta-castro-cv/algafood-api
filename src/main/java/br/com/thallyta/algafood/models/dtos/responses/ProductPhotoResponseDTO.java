package br.com.thallyta.algafood.models.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoResponseDTO {

    private String fileName;
    private String contentType;
    private Long size;
    private String description;
}
