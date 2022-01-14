package dev.nazeem.problem.example.trait;

import java.util.Collections;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationAdviceTrait;

public interface CustomConstraintViolationAdviceTrait extends ConstraintViolationAdviceTrait  {

    @Override
    @ParametersAreNonnullByDefault
    default ResponseEntity<Problem> handleConstraintViolation(final ConstraintViolationException exception, final NativeWebRequest request) {
        if(showViolations()) {
            return ConstraintViolationAdviceTrait.super.handleConstraintViolation(exception, request);
        } else {
            return ConstraintViolationAdviceTrait.super.newConstraintViolationProblem(exception,
                    Collections.emptyList(), request);
        }
    }

    default boolean showViolations() {
        return false;
    }

}
