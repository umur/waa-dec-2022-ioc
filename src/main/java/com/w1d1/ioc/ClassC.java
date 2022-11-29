package com.w1d1.ioc;

@MyBean
public class ClassC {

    public ClassC(){
        System.out.println("Class C Constructor");
    }

    @Override
    public String toString(){
        return "Class C toString";
    }
}
