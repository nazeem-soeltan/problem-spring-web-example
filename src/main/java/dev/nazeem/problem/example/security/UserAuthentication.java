package dev.nazeem.problem.example.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication extends AbstractAuthenticationToken {

    private User user;

    public UserAuthentication(
            final User user,
            final Collection<? extends GrantedAuthority> authorities
    ) {
        super(authorities);
        this.user = user;
        super.setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public Object getCredentials(){
        return user.getName();
    }

}
