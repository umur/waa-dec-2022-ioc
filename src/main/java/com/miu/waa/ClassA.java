package com.miu.ea;

@MyBean
public class ClassA {
    @MyAutowired
    private ClassB b;

    public void print(){
        System.out.println("This is ClassA");
        b.print();
    }
}
