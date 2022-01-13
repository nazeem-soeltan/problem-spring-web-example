package dev.nazeem.problem.example.trait;

import static java.util.Optional.ofNullable;

import java.net.URI;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.spring.web.advice.AdviceTrait;

import dev.nazeem.problem.example.exceptions.ApiErrorException;

public interface ApiErrorAdviceTrait extends AdviceTrait {

    @ExceptionHandler(ApiErrorException.class)
    default ResponseEntity<Problem> handleApiErrorException(
            final ApiErrorException ex,
            final NativeWebRequest request
    ) {
        return create(Status.valueOf(ex.getStatus().value()), ex, request);
    }

    @Override
    @ParametersAreNonnullByDefault
    default ProblemBuilder prepare(final Throwable throwable, final StatusType status, final URI type) {
        final var exception = (ApiErrorException) throwable;

        final var title = ofNullable(exception.getSummary())
                .orElse(exception.getStatus().getReasonPhrase());

        return Problem.builder()
                .withType(type)
                .withTitle(title)
                .withStatus(status)
                .withDetail(exception.getReason())
                .withCause(ofNullable(throwable.getCause())
                        .filter(cause -> isCausalChainsEnabled())
                        .map(this::toProblem)
                        .orElse(null))
                .with("error-code", exception.getErrorCode());
    }

}
