package com.test.TestTask.util;

public class UserResponseError {
    private String message;
    public UserResponseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
