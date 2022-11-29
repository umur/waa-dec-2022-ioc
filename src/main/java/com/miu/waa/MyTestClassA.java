package com.miu.waa;

import com.miu.waa.annotations.MyAutoWired;
import com.miu.waa.annotations.MyBean;

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
