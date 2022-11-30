package classes;

import annotations.MyAutowired;
import annotations.MyBean;

@MyBean
public class ClassB {

    public void display() {
        System.out.println("This is class B display method.");
    }
}
