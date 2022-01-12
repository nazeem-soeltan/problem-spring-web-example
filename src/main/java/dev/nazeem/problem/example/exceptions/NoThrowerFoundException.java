package dev.nazeem.problem.example.exceptions;

public class NoThrowerFoundException extends RuntimeException {

    public NoThrowerFoundException(final String exceptionKey) {
        super(String.format("Key: '%s' does not have a thrower", exceptionKey));
    }

}
