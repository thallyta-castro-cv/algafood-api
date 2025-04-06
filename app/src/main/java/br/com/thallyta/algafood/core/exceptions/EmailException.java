package br.com.thallyta.algafood.core.exceptions;

public class EmailException extends RuntimeException{

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
