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
                     + "@accessService.userAuthenticatedEquals(returnObject.client.id) or "
                     + "@accessService.manageRestaurant(returnObject.restaurant.id)")
        public @interface CanGetUnique {}

        @PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULTAR_PEDIDOS') or "
                + "@accessService.userAuthenticatedEquals(#params.clientId) or"
                + "@accessService.manageRestaurant(#params.restaurantId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanSearch {}

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanCreate {}

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('GERENCIAR_PEDIDOS') or "
                + "@accessService.manageOrderRestaurant(#code))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanManageOrder {}

    }

    public @interface FormPayment {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }

    public @interface Cities {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }

    public @interface States {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }

    public @interface UserGroupsPermissions {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
                + "@accessService.userAuthenticatedEquals(#userId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanChangeOwnPassword { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
                + "@accessService.userAuthenticatedEquals(#userId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanChangeUser { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }


        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }

    public @interface Statistics {

        @PreAuthorize("hasAuthority('SCOPE_READ') and "
                + "hasAuthority('GERAR_RELATORIOS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }
}
