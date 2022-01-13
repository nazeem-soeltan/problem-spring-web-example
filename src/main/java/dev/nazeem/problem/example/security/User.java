package dev.nazeem.problem.example.security;

import java.security.Principal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User implements Principal {

    String username;

    String email;

    @Override
    public String getName() {
        return username;
    }

}
