package br.com.thallyta.algafood.services;

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

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return jwt.getClaim("userId");
    }

    public boolean manageRestaurant(Long restaurantId) {
        return restaurantRepository.existsResponsible(restaurantId, getUserId());
    }
}
