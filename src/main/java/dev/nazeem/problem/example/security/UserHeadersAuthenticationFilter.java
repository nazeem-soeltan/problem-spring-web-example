package dev.nazeem.problem.example.security;

import static dev.nazeem.problem.example.security.UserHeaders.AUTHORITIES_HEADER;
import static dev.nazeem.problem.example.security.UserHeaders.EMAIL_HEADER;
import static dev.nazeem.problem.example.security.UserHeaders.USERNAME_HEADER;
import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class UserHeadersAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public UserHeadersAuthenticationFilter(final RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(
            final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse
    ) throws AuthenticationException {

        final var username = httpServletRequest.getHeader(USERNAME_HEADER.getValue());
        final var email = httpServletRequest.getHeader(EMAIL_HEADER.getValue());
        final var authorities = httpServletRequest.getHeader(AUTHORITIES_HEADER.getValue());

        final List<String> parsedAuthorities = nonNull(authorities)
                ? stream(authorities.split(", "))
                .map(String::trim).toList()
                : Collections.emptyList();

        return getAuthenticationManager()
                .authenticate(createUserAuthentication(username, email, parsedAuthorities));
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult
    ) throws IOException, ServletException {

        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        chain.doFilter(request, response);
    }

    private UserAuthentication createUserAuthentication(
            final String username,
            final String email,
            final List<String> authorities) {
        return new UserAuthentication(
                User.builder()
                        .username(username)
                        .email(email)
                        .build(),
                authorities.stream()
                        .map(Object::toString)
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );
    }

}
