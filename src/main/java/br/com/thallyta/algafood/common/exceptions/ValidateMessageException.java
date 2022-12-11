package br.com.thallyta.algafood.common.exceptions;

public class ValidateMessageException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ValidateMessageException(String Message) {
        super(Message);
    }
}
