package application;

import framework.MyAutoWired;
import framework.MyBean;

@MyBean
public class Class3 {
    @MyAutoWired
    private Class1 cs1;
    public void print(){
        System.out.println("Hello from Class3");
    }
}
