package com.phaser.project.domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<String> errors;

    public ValidationException(List<String> errors) {
        this.errors = errors;
    }

    public ValidationException(String error) {
        this.errors = new ArrayList<>();
        this.errors.add(error);

    }

    public List<String> getErrors() {
        return errors;
    }


}