package com.waalab1.ioc;
@MyBean
public class AClass {
    @MyAutowired
    private  BClass b;
    public AClass(){
        System.out.println("From A class Constructor");
    }
    @Override
    public String toString(){
        return "From A class to string";
    }
}
