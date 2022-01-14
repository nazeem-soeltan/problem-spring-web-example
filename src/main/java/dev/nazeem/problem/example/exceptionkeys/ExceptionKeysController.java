package dev.nazeem.problem.example.exceptionkeys;

import static dev.nazeem.problem.example.security.UserRoles.ROLE_DEVELOPER;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.nazeem.problem.example.thrower.ExceptionThrowerService;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping(path = ExceptionKeysController.PATH)
@RequiredArgsConstructor
public class ExceptionKeysController {

    public static final String PATH = "/api/exception-keys";

    private final ExceptionThrowerService exceptionThrower;

    @GetMapping(
            produces = {APPLICATION_JSON_VALUE, APPLICATION_PROBLEM_JSON_VALUE})
    @ResponseStatus(OK)
    public ExceptionKeysResponse getExceptionKeys() {
        return ExceptionKeysResponse.builder()
                .keys(exceptionThrower.getExceptionKeys())
                .build();
    }

    @GetMapping(
            path = "/{exception-key}",
            produces = {APPLICATION_PROBLEM_JSON_VALUE})
    @ResponseStatus(OK)
    @Secured({ROLE_DEVELOPER})
    public void throwException(
            @PathVariable("exception-key") final String exceptionKey
    ) {
        exceptionThrower.throwException(exceptionKey);
    }

    @PostMapping(
            produces = {APPLICATION_PROBLEM_JSON_VALUE})
    @ResponseStatus(OK)
    @Secured({ROLE_DEVELOPER})
    public void throwCustomException(
            @Valid @RequestBody final ExceptionKeysRequest request
    ) {
        exceptionThrower.throwCustomException(request.getKey(), request.getStatus());
    }

}
