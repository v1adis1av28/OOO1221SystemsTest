package com.test.TestTask.exceptions;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String msg) {
        super(msg);
    }
}
