package com.test.TestTask.exceptions;

public class DishAlreadyExistException extends RuntimeException{
    public DishAlreadyExistException(String msg){
        super(msg);
    }
}
