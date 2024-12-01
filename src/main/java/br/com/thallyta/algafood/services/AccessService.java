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
        return jwt.getClaim("userId");
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

}
