package dev.nazeem.problem.example.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;

import dev.nazeem.problem.example.trait.DefaultProblemHandler;

@ControllerAdvice
public class ExceptionHandler implements DefaultProblemHandler {
    // Put some further custom 'non-default' processing here, if needed.
}
