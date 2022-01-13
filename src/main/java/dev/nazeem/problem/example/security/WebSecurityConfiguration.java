package dev.nazeem.problem.example.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String apiPath;

    private final ObjectMapper objectMapper;

    public WebSecurityConfiguration(
            @Value("${api.security.path.api:}") final String apiPath,
            final ObjectMapper objectMapper
    ) {
        super();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        this.apiPath = apiPath;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        //@formatter:off
        http.csrf().disable();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.authorizeRequests().antMatchers(apiPath).authenticated();
        http.addFilterBefore(userHeadersAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //@formatter:on
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userAuthenticationProvider());
    }

    @Bean
    protected AbstractAuthenticationProcessingFilter userHeadersAuthenticationFilter()
            throws Exception {
        final UserHeadersAuthenticationFilter filter = new UserHeadersAuthenticationFilter(
                new AntPathRequestMatcher(apiPath));

        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationFailureHandler(failureHandler());

        return filter;
    }

    @Bean
    protected AuthenticationProvider userAuthenticationProvider() {
        return new UserAuthenticationProvider();
    }

    // FIXME: Seems cumbersome to do this in this way. Investigate to ommit this.
    @Bean
    AuthenticationFailureHandler failureHandler() {
        return (request, response, exception) -> {
            response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            final Problem problem = Problem.builder()
                    .withStatus(Status.valueOf(HttpServletResponse.SC_UNAUTHORIZED))
                    .withTitle("Unauthorized")
                    .withDetail(exception.getMessage())
                    .build();

            objectMapper.writeValue(response.getOutputStream(), problem);
        };
    }

}
