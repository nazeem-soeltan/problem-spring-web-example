package dev.nazeem.problem.example.trait;

import static java.util.Objects.isNull;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.violations.ConstraintViolationProblem;

public class TraceableProblemCreator {

    public static ResponseEntity<Problem> create(final ResponseEntity<Problem> problem, final String traceId) {
        if(isNull(traceId)) {
            return problem;
        }

        final Problem body = problem.getBody();

        if(isNull(body)) {
            return problem;
        }

        Problem newProblem;
        if (body instanceof ConstraintViolationProblem) {
            newProblem = new TraceableConstraintViolationProblem(body.getType(), body.getStatus(), ((ConstraintViolationProblem) body).getViolations(), traceId);
        } else {
            newProblem = buildThrowableProblem(body, traceId);
        }

        return createResponse(problem, newProblem);
    }

    private static Problem buildThrowableProblem(final Problem problem, final String traceId) {
        final ProblemBuilder problemBuilder = Problem.builder();

        problem.getParameters().forEach(problemBuilder::with);
        problemBuilder.with("trace-id", traceId);

        if(problem instanceof ThrowableProblem) {
            problemBuilder.withCause(((ThrowableProblem) problem).getCause());
        }

        return problemBuilder
                .withInstance(problem.getInstance())
                .withTitle(problem.getTitle())
                .withStatus(problem.getStatus())
                .withDetail(problem.getDetail())
                .build();
    }

    private static ResponseEntity<Problem> createResponse(final ResponseEntity<Problem> oldProblem, final Problem newProblem) {
        return ResponseEntity.status(oldProblem.getStatusCode())
                .headers(oldProblem.getHeaders())
                .body(newProblem);
    }

}
