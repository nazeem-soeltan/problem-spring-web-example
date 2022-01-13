package dev.nazeem.problem.example.trait;

import java.net.URI;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.StatusType;
import org.zalando.problem.spring.web.advice.general.ThrowableAdviceTrait;

public interface DefaultThrowableAdviceTrait extends ThrowableAdviceTrait {

    String DEFAULT_DETAIL = "An error occurred";

    @Override
    @ParametersAreNonnullByDefault
    default ProblemBuilder prepare(final Throwable throwable, final StatusType status, final URI type) {
        return Problem.builder()
                .withType(type)
                .withTitle(status.getReasonPhrase())
                .withStatus(status)
                .withDetail(enableDefaultDetail() ? DEFAULT_DETAIL : throwable.getMessage())
                .withCause(Optional.ofNullable(throwable.getCause())
                        .filter(cause -> isCausalChainsEnabled())
                        .map(this::toProblem)
                        .orElse(null));
    }

    default boolean enableDefaultDetail() {
        return true;
    }

}
