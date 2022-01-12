package dev.nazeem.problem.example.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;

import dev.nazeem.problem.example.trait.DefaultProblemHandling;

@ControllerAdvice
public class ExceptionHandler implements DefaultProblemHandling {

}
