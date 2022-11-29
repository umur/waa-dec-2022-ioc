package com.w1d1.ioc;

public class BeanNotFoundException extends Exception {
    public BeanNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public BeanNotFoundException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
