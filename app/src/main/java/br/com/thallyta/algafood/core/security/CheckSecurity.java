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
        @PreAuthorize("@accessService.canGetKitchens()")
        public @interface CanGet {}

    }

    public @interface Restaurants {

        @PreAuthorize("@accessService.canManageRegisterRestaurants()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }

        @PreAuthorize("@accessService.canManageOperationRestaurant(#restaurantId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanManagerOperation { }

        @PreAuthorize("@accessService.canGetRestaurants()")
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

        @PreAuthorize("@accessService.canSearchOrders(#params.clientId, #params.restaurantId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanSearch {}

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanCreate {}

        @PreAuthorize("@accessService.canManageOrders(#code)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanManageOrder {}

    }

    public @interface FormPayment {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }

        @PreAuthorize("@accessService.canGetFormPayment()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }

    public @interface Cities {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }

        @PreAuthorize("@accessService.canGetCities()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }

    public @interface States {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }

        @PreAuthorize("@accessService.canGetStates()")
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

        @PreAuthorize("@accessService.canEditUserGroupPermission()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }

        @PreAuthorize("@accessService.canGetUserGroupPermission()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }

    public @interface Statistics {

        @PreAuthorize("@accessService.canGetStatistics()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanGet { }

    }
}
