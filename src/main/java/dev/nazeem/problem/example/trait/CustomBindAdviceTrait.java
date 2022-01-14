package dev.nazeem.problem.example.trait;

import java.util.Collections;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.validation.BindAdviceTrait;

public interface CustomBindAdviceTrait extends BindAdviceTrait {

    @Override
    @ParametersAreNonnullByDefault
    default ResponseEntity<Problem> handleBindingResult(final BindException exception, final NativeWebRequest request) {
        if(showViolations()) {
            return BindAdviceTrait.super.handleBindingResult(exception, request);
        } else {
            return BindAdviceTrait.super.newConstraintViolationProblem(exception,
                    Collections.emptyList(), request);
        }
    }

    default boolean showViolations() {
        return false;
    }

}
