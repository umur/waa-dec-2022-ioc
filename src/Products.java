package src;

import src.annotation.MyAutoWired;
import src.annotation.MyBeans;

@MyBeans
public class Products {

    @MyAutoWired
    private Reviews reviews;

}
