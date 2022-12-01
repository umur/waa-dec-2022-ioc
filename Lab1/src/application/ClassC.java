package application;

import framework.MyAutoWired;
import framework.MyBean;

@MyBean
public class ClassC {
    @MyAutoWired
    private ClassA a;

    @MyAutoWired
    private ClassB b;
    public void message(){
        a.message();
        System.out.println("Hello from ClassB");
        b.message();
    }
}
