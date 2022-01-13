package dev.nazeem.problem.example.trait;

import org.zalando.problem.spring.web.advice.general.GeneralAdviceTrait;
import org.zalando.problem.spring.web.advice.http.HttpAdviceTrait;
import org.zalando.problem.spring.web.advice.io.IOAdviceTrait;
import org.zalando.problem.spring.web.advice.network.NetworkAdviceTrait;
import org.zalando.problem.spring.web.advice.routing.RoutingAdviceTrait;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

public interface BaseProblemHandler extends GeneralAdviceTrait, HttpAdviceTrait, IOAdviceTrait, NetworkAdviceTrait, RoutingAdviceTrait, SecurityAdviceTrait {
    // Problem Spring Web's selection.
}
