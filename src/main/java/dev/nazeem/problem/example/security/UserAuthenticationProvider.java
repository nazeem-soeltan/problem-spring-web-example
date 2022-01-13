package dev.nazeem.problem.example.security;

import static java.util.Objects.isNull;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class UserAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        if (isNull(authentication.getName())) {
            throw new BadCredentialsException("Username is missing");
        }

        return authentication;
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return UserAuthentication.class.equals(aClass);
    }
}
