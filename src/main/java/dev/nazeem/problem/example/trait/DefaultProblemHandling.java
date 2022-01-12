package dev.nazeem.problem.example.trait;

import java.net.URI;

import javax.annotation.ParametersAreNonnullByDefault;

import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.StatusType;

import dev.nazeem.problem.example.exceptions.ApiErrorException;

public interface DefaultProblemHandling extends ApiErrorTrait, BasicThrowableTrait {

    @Override
    @ParametersAreNonnullByDefault
    default ProblemBuilder prepare(
            final Throwable throwable, final StatusType status, final URI type
    ) {
        if (throwable instanceof ApiErrorException) {
            return ApiErrorTrait.super.prepare(throwable, status, type);
        } else {
            return BasicThrowableTrait.super.prepare(throwable, status, type);
        }
    }

}
