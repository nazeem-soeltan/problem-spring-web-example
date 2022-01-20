package dev.nazeem.problem.example.trait;

import static java.util.Optional.ofNullable;

import java.net.URI;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.StatusType;
import org.zalando.problem.spring.web.advice.general.ResponseStatusAdviceTrait;

import dev.nazeem.problem.example.exceptions.ApiErrorException;

public interface ApiErrorAdviceTrait extends ResponseStatusAdviceTrait {

    @ExceptionHandler(ApiErrorException.class)
    default ResponseEntity<Problem> handleApiErrorException(
            final ApiErrorException ex,
            final NativeWebRequest request
    ) {
        return ResponseStatusAdviceTrait.super.handleResponseStatusException(ex, request);
    }

    @Override
    @ParametersAreNonnullByDefault
    default ProblemBuilder prepare(final Throwable throwable, final StatusType status, final URI type) {
        final var exception = (ApiErrorException) throwable;

        final String title = ofNullable(exception.getSummary())
                .orElse(exception.getStatus().getReasonPhrase());

        final ProblemBuilder builder = ResponseStatusAdviceTrait.super.prepare(throwable, status, type);

        final String errorCode = exception.getErrorCode();
        if(StringUtils.hasText(errorCode)) {
            builder.with(DefaultParamKeys.ERROR_CODE.getKeyValue(), errorCode);
        }

        return builder
                .withTitle(title)
                .withDetail(exception.getReason());
    }

}
