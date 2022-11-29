package com.w1d1.ioc;

@MyBean
public class ClassB {
    @MyAutowired
    private ClassC c;

    public ClassB(){
        System.out.println("Class B Constructor");
    }

    @Override
    public String toString(){
        return "Class B toString";
    }
}