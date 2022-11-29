package com.w1d1.ioc;

@MyBean
public class ClassA {
    @MyAutowired
    private ClassB b;

    public ClassA(){
        System.out.println("Class A Constructor");
    }

    @Override
    public String toString(){
        return "Class A toString";
    }
}
