package br.com.thallyta.algafood.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    public @interface Kitchen {

        @Retention(RUNTIME)
        @Target(METHOD)
        @PreAuthorize("hasAuthority('EDITAR_COZINHAS') and hasAuthority('SCOPE_WRITE')")
        public @interface CanEdit {}

        @Retention(RUNTIME)
        @Target(METHOD)
        @PreAuthorize("isAuthenticated() and hasAuthority('SCOPE_READ')")
        public @interface CanGet {}

    }

    public @interface Restaurants {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') "
                + " and hasAuthority('EDITAR_RESTAURANTES') ")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }

        @PreAuthorize("(hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')) "
                + " or @accessService.manageRestaurant(#restaurantId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanManagerOperation { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }

    public @interface Order {

        @Retention(RUNTIME)
        @Target(METHOD)
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
                     + "@accessService.getUserId() == returnObject.client.id or "
                     + "@accessService.manageRestaurant(returnObject.restaurant.id)")
        public @interface CanGetUnique {}

    }
}
