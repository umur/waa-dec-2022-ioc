package edu.miu.waa.w1d1.application;
import edu.miu.waa.w1d1.ioc.annotations.MyAutowired;
import edu.miu.waa.w1d1.ioc.annotations.MyBean;

@MyBean
public class ClassA {
    @MyAutowired
    ClassB classB;

    public ClassA(){
        System.out.println("ClassA Constructor");
    }
}
