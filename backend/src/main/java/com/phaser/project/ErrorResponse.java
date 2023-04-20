package com.phaser.project;

import java.util.List;

public class ErrorResponse {

    public ErrorResponse(String message, List<String> details){
        this.details = details;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }

    private final String message;
    private final List<String> details;
}