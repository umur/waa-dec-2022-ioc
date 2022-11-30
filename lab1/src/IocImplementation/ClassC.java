package IocImplementation;

import MyAnnotations.MyAutowired;
import MyAnnotations.MyBean;

@MyBean
public class ClassC {

    @MyAutowired
    private ClassB b;
}
