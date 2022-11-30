package classes;

import annotations.MyAutowired;
import annotations.MyBean;

@MyBean
public class ClassA {
    @MyAutowired
    private ClassB bb;

//    public ClassA(ClassB b) {
//
//    }

    public void display() {
        bb.display();
        System.out.println("This is class A display method.");
    }
}
