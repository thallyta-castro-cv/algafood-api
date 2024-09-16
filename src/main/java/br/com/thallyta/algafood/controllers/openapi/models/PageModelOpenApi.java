package br.com.thallyta.algafood.controllers.openapi.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageModelOpenApi<T> {

    List<T> content;
    private Long size;
    private Long totalElements;
    private Long totalPages;
    private Long number;
}
