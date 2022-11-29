package com.waalab1.ioc;
@MyBean
public class CClass {
    public CClass(){
        System.out.println("From C class Constructor");
    }
    @Override
    public String toString(){
        return "From C class to string";
    }
}
