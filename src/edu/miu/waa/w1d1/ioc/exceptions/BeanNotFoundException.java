package edu.miu.waa.w1d1.ioc.exceptions;

public class BeanNotFoundException extends RuntimeException{
    public BeanNotFoundException(String error){
        super(error);
    }
}
