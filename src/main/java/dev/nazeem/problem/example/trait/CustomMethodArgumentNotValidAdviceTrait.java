package dev.nazeem.problem.example.trait;

import java.util.Collections;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.validation.MethodArgumentNotValidAdviceTrait;

public interface CustomMethodArgumentNotValidAdviceTrait extends MethodArgumentNotValidAdviceTrait  {

    @Override
    @ParametersAreNonnullByDefault
    default ResponseEntity<Problem> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception, final NativeWebRequest request) {
        if(showViolations()) {
            return MethodArgumentNotValidAdviceTrait.super.handleMethodArgumentNotValid(exception, request);
        } else {
            return MethodArgumentNotValidAdviceTrait.super.newConstraintViolationProblem(exception,
                    Collections.emptyList(), request);
        }
    }

    default boolean showViolations() {
        return false;
    }

}
