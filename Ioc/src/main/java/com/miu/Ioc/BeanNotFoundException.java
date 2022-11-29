package com.miu.Ioc;

public class BeanNotFoundException extends RuntimeException {
    public BeanNotFoundException(String str) {
        super(str);
    }
}