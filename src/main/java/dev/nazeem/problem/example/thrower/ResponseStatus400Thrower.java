package dev.nazeem.problem.example.thrower;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import dev.nazeem.problem.example.exceptions.ApiErrorException;

@Component
public class ResponseStatus400Thrower implements Thrower {

    @Override
    public void throwNow() {
        throw new ApiErrorException(HttpStatus.BAD_REQUEST, "400 thrown");
    }

    @Override
    public String getKey() {
        return "400";
    }
}
