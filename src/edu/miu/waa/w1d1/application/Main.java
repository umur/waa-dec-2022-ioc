package edu.miu.waa.w1d1.application;

import edu.miu.waa.w1d1.ioc.injectors.MyInjector;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        MyInjector myInjector=new MyInjector(Main.class.getPackageName());


        myInjector.getBean(ClassB.class);
        myInjector.getBean(ClassA.class);
        myInjector.getBean(Main.class);


    }
}