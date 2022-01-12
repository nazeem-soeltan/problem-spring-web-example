package dev.nazeem.problem.example.trait;

import java.net.URI;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.spring.web.advice.AdviceTrait;


public interface ResponseStatusTrait extends AdviceTrait {

    @ExceptionHandler(ResponseStatusException.class)
    default ResponseEntity<Problem> handleResponseStatusException(
            final ResponseStatusException ex,
            final NativeWebRequest request
    ) {
        return create(Status.valueOf(ex.getStatus().value()), ex, request);
    }

    @Override
    @ParametersAreNonnullByDefault
    default ProblemBuilder prepare(final Throwable throwable, final StatusType status, final URI type) {
        final var exception = (ResponseStatusException) throwable;

        return Problem.builder()
                .withType(type)
                .withTitle(exception.getStatus().getReasonPhrase())
                .withStatus(Status.valueOf(exception.getStatus().value()))
                .withDetail(exception.getReason())
                .withCause(Optional.ofNullable(throwable.getCause())
                        .filter(cause -> isCausalChainsEnabled())
                        .map(this::toProblem)
                        .orElse(null));
    }

}
