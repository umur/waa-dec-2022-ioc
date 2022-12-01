package edu.miu;
import java.io.IOException;

public class ApplicationEnterClass {

    public static void main(String[] args) throws NoSuchMethodException, IOException, BeanNotFoundException {
        MyInjector myInjector = new MyInjector();
        myInjector.createInstanceForAllMyBeanClass();
        Product bean = (Product)myInjector.getBean(Product.class);
        bean.getTheReviewComments();
    }
}
