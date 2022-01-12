package dev.nazeem.problem.example.thrower;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import dev.nazeem.problem.example.exceptions.ApiErrorException;

@Component
public class ResponseStatus503Thrower implements Thrower {

    @Override
    public void throwNow() {
        throw new ApiErrorException("5.1", "This is a custom title", HttpStatus.SERVICE_UNAVAILABLE, "Throws 503");
    }

    @Override
    public String getKey() { return "503"; }
}
