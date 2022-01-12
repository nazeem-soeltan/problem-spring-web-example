package dev.nazeem.problem.example.thrower;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import dev.nazeem.problem.example.exceptions.CustomApiErrorException;

@Component
public class ResponseStatus422Thrower implements Thrower {

    @Override
    public void throwNow() {
        throw new CustomApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Throws 422");
    }

    @Override
    public String getKey() {
        return "422";
    }

}
