package br.com.thallyta.algafood.core.authorizationserver;

import br.com.thallyta.algafood.models.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class AuthUser extends User implements UserDetails {

    private final Long userId;
    private final String fullName;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        this.userId = user.getId();
        this.fullName = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
