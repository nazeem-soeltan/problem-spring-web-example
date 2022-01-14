package dev.nazeem.problem.example.trait;

import org.zalando.problem.spring.web.advice.general.ProblemAdviceTrait;
import org.zalando.problem.spring.web.advice.general.ResponseStatusAdviceTrait;
import org.zalando.problem.spring.web.advice.general.UnsupportedOperationAdviceTrait;
import org.zalando.problem.spring.web.advice.http.HttpAdviceTrait;
import org.zalando.problem.spring.web.advice.io.IOAdviceTrait;
import org.zalando.problem.spring.web.advice.network.NetworkAdviceTrait;
import org.zalando.problem.spring.web.advice.routing.RoutingAdviceTrait;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

public interface BaseProblemHandler extends HttpAdviceTrait, IOAdviceTrait, NetworkAdviceTrait, RoutingAdviceTrait, SecurityAdviceTrait,
        ProblemAdviceTrait, ResponseStatusAdviceTrait, UnsupportedOperationAdviceTrait {
    // Selection of library provided AdviceTraits.
}
