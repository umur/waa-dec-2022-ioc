package com.miu.Ioc;

import com.miu.Ioc.MyAutoWired;
import com.miu.Ioc.MyBean;

@MyBean
public class MyTestClassA {
    @MyAutoWired
    private MyTestClassB b;

    public void print() {
        System.out.println("This is MyTestClassA");

        System.out.println("Here b object is used:");
        b.print();
    }
}
