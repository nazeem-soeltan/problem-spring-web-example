package dev.nazeem.problem.example.exceptionkeys;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.nazeem.problem.example.thrower.ExceptionThrowerService;
import lombok.RequiredArgsConstructor;

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
    public void throwException(
            @PathVariable("exception-key") final String exceptionKey
    ) {
        exceptionThrower.throwException(exceptionKey);
    }

}
