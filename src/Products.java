package src;

import src.annotation.MyAutoWired;
import src.annotation.MyBeans;

@MyBeans
public class Products {

    @MyAutoWired
    private String name;

    @MyAutoWired
    private int price;

    private Products(String name, int price){
        this.name = name;
        this.price  = price;
    }

    private String getName(){
        return this.name;
    }

    private int getPrice(){
        return this.price;
    }

}
