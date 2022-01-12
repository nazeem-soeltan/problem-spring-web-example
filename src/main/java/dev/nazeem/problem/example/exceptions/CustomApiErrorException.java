package dev.nazeem.problem.example.exceptions;

import org.springframework.http.HttpStatus;

public class CustomApiErrorException extends ApiErrorException {

    public CustomApiErrorException(
            final HttpStatus httpStatus,
            final String detail
    ) {
        super("Custom title", httpStatus, detail);
    }
}
