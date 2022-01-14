package dev.nazeem.problem.example.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;

import dev.nazeem.problem.example.trait.ApiErrorHandler;

@ControllerAdvice
public class ExceptionHandler implements ApiErrorHandler {

    // Put some further custom 'non-default' processing here, if needed.

}
