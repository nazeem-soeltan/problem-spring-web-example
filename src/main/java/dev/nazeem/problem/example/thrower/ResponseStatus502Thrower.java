package dev.nazeem.problem.example.thrower;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import dev.nazeem.problem.example.exceptions.ApiErrorException;

@Component
public class ResponseStatus502Thrower implements Thrower {

    @Override
    public void throwNow() {
        throw new ApiErrorException(HttpStatus.BAD_GATEWAY, "502 thrown");
    }

    @Override
    public String getKey() {
        return "502";
    }

}
