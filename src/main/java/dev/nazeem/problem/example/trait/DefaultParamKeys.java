package dev.nazeem.problem.example.trait;

public enum DefaultParamKeys {

    TRACE_ID("trace-id"),
    ERROR_CODE("error-code")
    ;

    private final String keyValue;

    DefaultParamKeys(final String keyValue) {
        this.keyValue = keyValue;
    }

    public String getKeyValue() {
        return keyValue;
    }
}
