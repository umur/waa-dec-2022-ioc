package com.example.waadecioc;
@MyBean
public class ClassA {
    @MyAutowired
    public ClassB classB;

    public String display(){
        return  classB.myString();
    }
}
