package br.com.thallyta.algafood.common.exceptions;

import lombok.Data;

@Data
public class NotFound extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotFound(String message) {
        super(message);
    }
}
