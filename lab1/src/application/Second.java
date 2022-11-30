package application;

import contatiner.MyAutoWired;

public class Second {
    @MyAutoWired
    private First first;
    public void print(){
        first.print();
        System.out.println("My Second");
    }
}
