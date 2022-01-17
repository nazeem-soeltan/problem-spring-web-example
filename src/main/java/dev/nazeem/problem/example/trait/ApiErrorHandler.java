package dev.nazeem.problem.example.trait;

import java.net.URI;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.StatusType;

import dev.nazeem.problem.example.exceptions.ApiErrorException;

public interface ApiErrorHandler extends BaseProblemHandler, ApiErrorAdviceTrait,
        CustomConstraintViolationAdviceTrait, CustomMethodArgumentNotValidAdviceTrait, CustomBindAdviceTrait,
        DefaultThrowableAdviceTrait {

    @Override
    @ParametersAreNonnullByDefault
    default ProblemBuilder prepare(
            final Throwable throwable, final StatusType status, final URI type
    ) {
        if (throwable instanceof ApiErrorException) {
            return ApiErrorAdviceTrait.super.prepare(throwable, status, type);
        } else {
            return DefaultThrowableAdviceTrait.super.prepare(throwable, status, type);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    default ResponseEntity<Problem> process(
            ResponseEntity<Problem> entity, NativeWebRequest request
    ) {
        final ResponseEntity<Problem> problemResponse = TraceableProblemCreator.create(entity, getTraceId());

        return processAfter(problemResponse, request);
    }

    default ResponseEntity<Problem> processAfter(ResponseEntity<Problem> entity, NativeWebRequest request) {
        return entity;
    }

    @Override
    default boolean showViolations() {
        return false;
    }

    default String getTraceId() {
        return "some-trace-id";
    }

}
