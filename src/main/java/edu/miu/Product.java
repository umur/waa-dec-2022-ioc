package edu.miu;

import java.util.List;

@MyBean
public class Product {

    @MyAutowired
    private Review reviews;

    public void getTheReviewComments(){
        reviews.comments();
    }
}
