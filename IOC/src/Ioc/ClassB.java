package Ioc;

import Annotations.MyAutoWired;
import Annotations.MyBean;

@MyBean
public class ClassB {
    @MyAutoWired
    ClassA a;
}
