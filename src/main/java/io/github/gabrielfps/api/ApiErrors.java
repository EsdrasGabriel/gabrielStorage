package io.github.gabrielfps.api;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Data
public class ApiErrors {
    private List<String> errors;
    public ApiErrors(String errors) {
        this.errors = Collections.singletonList(errors);
    }
}
