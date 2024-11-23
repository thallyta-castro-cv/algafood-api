package br.com.thallyta.algafood.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    public @interface Kitchen {
        @Retention(RUNTIME)
        @Target(METHOD)
        @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
        public @interface CanEdit {}

        @Retention(RUNTIME)
        @Target(METHOD)
        @PreAuthorize("isAuthenticated()")
        public @interface CanGet {}

    }
}
