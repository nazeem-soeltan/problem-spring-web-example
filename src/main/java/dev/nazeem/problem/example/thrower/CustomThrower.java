package dev.nazeem.problem.example.thrower;

import org.springframework.http.HttpStatus;

import dev.nazeem.problem.example.exceptions.CustomApiErrorException;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class CustomThrower implements Thrower {

    String key;

    HttpStatus status;

    @Override
    public void throwNow() {
        throw new CustomApiErrorException(status, String.format("%s thrown", key));
    }

    @Override
    public String getKey() {
        return key;
    }

}
