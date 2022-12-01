package edu.miu;

public class BeanNotFoundException extends Exception{
    public BeanNotFoundException(String message) {
        super(message);
    }

    public BeanNotFoundException() {
        super();
    }
}
