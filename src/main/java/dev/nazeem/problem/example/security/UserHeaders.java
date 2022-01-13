package dev.nazeem.problem.example.security;

import lombok.Getter;

public enum UserHeaders {

    USERNAME_HEADER("X-Consumer-Username"),
    EMAIL_HEADER("X-Consumer-Email"),
    AUTHORITIES_HEADER("X-Consumer-Authorities");

    @Getter
    private final String value;

    UserHeaders(final String value) {
        this.value = value;
    }

}
