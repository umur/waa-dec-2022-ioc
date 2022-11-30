package application;

import framework.MyAutoWired;
import framework.MyBean;

@MyBean
public class Class1 {
    @MyAutoWired
    private Class2 class2;

    public void print(){
        System.out.println("Hello from Class1");
        class2.print();
    }
}
