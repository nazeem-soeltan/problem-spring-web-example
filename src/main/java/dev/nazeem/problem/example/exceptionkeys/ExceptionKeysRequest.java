package dev.nazeem.problem.example.exceptionkeys;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
public class ExceptionKeysRequest {

    @NotBlank
    String key;

    int status;

    @AssertTrue(message = "key can't have value 'none'")
    public boolean isKeyValid() {
        return !"none".equalsIgnoreCase(key);
    }

}
