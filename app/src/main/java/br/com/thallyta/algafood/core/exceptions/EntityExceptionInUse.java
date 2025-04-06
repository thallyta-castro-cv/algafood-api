package br.com.thallyta.algafood.core.exceptions;

public class EntityExceptionInUse extends RuntimeException{

    public EntityExceptionInUse(String message) {
        super(message);
    }
}
