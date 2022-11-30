package edu.miu.waa.w1d1.application;

import edu.miu.waa.w1d1.ioc.annotations.MyBean;

@MyBean
public class ClassB {
    public ClassB(){
        System.out.println("ClassB Constructor");
    }
}
