package dev.nazeem.problem.example.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BookingDraftIncompleteException extends ApiErrorException {

    public BookingDraftIncompleteException(
            final String detail
    ) {
        super("Booking draft incomplete", BAD_REQUEST, detail);
    }

}
