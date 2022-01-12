package dev.nazeem.problem.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;

public class ApiErrorException extends ResponseStatusException  {

    @Getter
    private final String errorCode;

    @Getter
    private final String summary;

    public ApiErrorException(final String errorCode, final String summary, final HttpStatus status, final String detail) {
        super(status, detail);
        this.errorCode = errorCode;
        this.summary = summary;
    }

    public ApiErrorException(final String summary, final HttpStatus httpStatus, final String detail) {
        this(null, summary, httpStatus, detail);
    }

    public ApiErrorException(final HttpStatus httpStatus, final String detail) {
        this(null, null, httpStatus, detail);
    }

}
