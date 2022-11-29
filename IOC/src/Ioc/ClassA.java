package Ioc;

import Annotations.MyAutoWired;
import Annotations.MyBean;

@MyBean
public class ClassA {

    @MyAutoWired
    ClassB b;
}
