package com.example.demo;
@MyBean
public class ClassA {
    @MyAutowired
    public ClassB classB;

    public String display(){
        return  classB.myString();
    }
}
