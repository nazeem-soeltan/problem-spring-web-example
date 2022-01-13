package dev.nazeem.problem.example.trait;

import java.net.URI;

import javax.annotation.ParametersAreNonnullByDefault;

import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.StatusType;

import dev.nazeem.problem.example.exceptions.ApiErrorException;

public interface ApiErrorHandler extends BaseProblemHandler, ApiErrorAdviceTrait, DefaultThrowableAdviceTrait {

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

}
