package src;

import src.annotation.MyAutoWired;
import src.annotation.MyBeans;

@MyBeans
public class Reviews {

    @MyAutoWired
    private Products products;
}
