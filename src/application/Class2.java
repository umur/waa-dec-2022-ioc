package application;

import framework.MyAutoWired;
import framework.MyBean;

@MyBean
public class Class2 {
//    @MyAutoWired
//    private Class1 c1;

    @MyAutoWired
    private Class3 c3;

    public void print(){
        //c1.print();
        System.out.println("Hello from Class2");
        c3.print();
    }
}
