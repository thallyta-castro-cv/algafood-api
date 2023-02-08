package br.com.thallyta.algafood.common.exceptions;

public class EntityExceptionInUse extends RuntimeException{

    public EntityExceptionInUse(String message) {
        super(message);
    }
}
