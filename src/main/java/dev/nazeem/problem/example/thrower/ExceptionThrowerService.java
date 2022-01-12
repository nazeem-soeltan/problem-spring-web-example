package dev.nazeem.problem.example.thrower;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.nazeem.problem.example.exceptions.NoThrowerFoundException;

@Service
public class ExceptionThrowerService {

    private final Map<String, Thrower> throwerMap;

    public ExceptionThrowerService(final List<Thrower> throwers) {
        this.throwerMap = throwers.stream()
                .collect(Collectors.toMap(Thrower::getKey, Function.identity()));
    }

    public void throwException(final String exceptionKey) {
        final Thrower thrower = throwerMap.get(exceptionKey);

        if(thrower == null) {
            throw new NoThrowerFoundException(exceptionKey);
        }

        thrower.throwNow();
    }

    public Set<String> getExceptionKeys() {
        return throwerMap.keySet();
    }
}
