package dev.nazeem.problem.example.thrower;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class AccessDeniedThrower implements Thrower {

    @Override
    public void throwNow() {
        throw new AccessDeniedException("Access denied thrown");
    }

    @Override
    public String getKey() {
        return "access-denied";
    }

}
