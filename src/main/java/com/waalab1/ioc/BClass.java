package com.waalab1.ioc;
@MyBean
public class BClass {
    @MyAutowired
    private  CClass c;
    public BClass(){
        System.out.println("From B class Constructor");
    }
    @Override
    public String toString(){
        return "From B class to string";
    }
}
