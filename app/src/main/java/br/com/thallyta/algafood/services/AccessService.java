package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.repositories.OrderRepository;
import br.com.thallyta.algafood.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AccessService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        Object userId = jwt.getClaim("userId");

        if(userId == null) {
            return null;
        }

        return Long.valueOf(userId.toString());
    }

    public boolean manageRestaurant(Long restaurantId) {
        if (restaurantId == null) {
            return false;
        }

        return restaurantRepository.existsResponsible(restaurantId, getUserId());
    }

    public boolean manageOrderRestaurant(String code) {
        return orderRepository.isOrderManageFor(code, getUserId());
    }

    public boolean userAuthenticatedEquals(Long userId) {
        return getUserId() != null && userId != null
                && getUserId().equals(userId);
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean canManageOrders(String code) {
        return hasAuthority("SCOPE_WRITE") && (hasAuthority("GERENCIAR_PEDIDOS")
                || manageOrderRestaurant(code));
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public boolean hasScopeWrite() {
        return hasAuthority("SCOPE_WRITE");
    }

    public boolean hasScopeRead() {
        return hasAuthority("SCOPE_READ");
    }

    public boolean canGetRestaurants() {
        return hasScopeRead() && isAuthenticated();
    }

    public boolean canManageRegisterRestaurants() {
        return hasScopeWrite() && hasAuthority("EDITAR_RESTAURANTES");
    }

    public boolean canManageOperationRestaurant(Long restaurantId) {
        return hasScopeWrite() && (hasAuthority("EDITAR_RESTAURANTES")
                || manageRestaurant(restaurantId));
    }

    public boolean canGetUserGroupPermission() {
        return hasScopeRead() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean canEditUserGroupPermission() {
        return hasScopeWrite() && hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean canSearchOrders(Long clientId, Long restaurantId) {
        return hasScopeRead() && (hasAuthority("CONSULTAR_PEDIDOS")
                || userAuthenticatedEquals(clientId) || manageRestaurant(restaurantId));
    }

    public boolean canSearchOrders() {
        return isAuthenticated() && hasScopeRead();
    }

    public boolean canGetFormPayment() {
        return isAuthenticated() && hasScopeRead();
    }

    public boolean canGetCities() {
        return isAuthenticated() && hasScopeRead();
    }

    public boolean canGetStates() {
        return isAuthenticated() && hasScopeRead();
    }

    public boolean canGetKitchens() {
        return isAuthenticated() && hasScopeRead();
    }

    public boolean canGetStatistics() {
        return hasScopeRead() && hasAuthority("GERAR_RELATORIOS");
    }

}
