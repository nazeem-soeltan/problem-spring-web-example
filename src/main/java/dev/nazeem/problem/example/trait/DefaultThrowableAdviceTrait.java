package dev.nazeem.problem.example.trait;

import java.net.URI;

import javax.annotation.ParametersAreNonnullByDefault;

import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.StatusType;
import org.zalando.problem.spring.web.advice.general.ThrowableAdviceTrait;

public interface DefaultThrowableAdviceTrait extends ThrowableAdviceTrait {

    @Override
    @ParametersAreNonnullByDefault
    default ProblemBuilder prepare(final Throwable throwable, final StatusType status, final URI type) {
        // Add custom preparation here for throwable classes.
        return ThrowableAdviceTrait.super.prepare(throwable, status, type);
    }

}
