package dev.nazeem.problem.example.exceptionkeys;

import java.util.Set;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExceptionKeysResponse {

    Set<String> keys;

}
