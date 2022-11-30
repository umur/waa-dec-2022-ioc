package IocImplementation;

import MyAnnotations.MyAutowired;
import MyAnnotations.MyBean;

@MyBean
public class ClassB {

    @MyAutowired
    private ClassA a;

}
