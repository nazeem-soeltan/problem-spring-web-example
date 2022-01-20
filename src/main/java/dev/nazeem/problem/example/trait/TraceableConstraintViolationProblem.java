package dev.nazeem.problem.example.trait;

import static dev.nazeem.problem.example.trait.DefaultParamKeys.TRACE_ID;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.zalando.problem.StatusType;
import org.zalando.problem.violations.ConstraintViolationProblem;
import org.zalando.problem.violations.Violation;

public final class TraceableConstraintViolationProblem extends ConstraintViolationProblem {

    private final Map<String, Object> parameters;

    public TraceableConstraintViolationProblem(
            final URI type, final StatusType status, final List<Violation> violations, final String traceId
    ) {
        super(type, status, violations);
        this.parameters = Map.of(TRACE_ID.getKeyValue(), traceId);
    }

    @Override
    public Map<String, Object> getParameters() {
        return this.parameters;
    }

}
