

import classcoll.ClassA;
import classcoll.ClassB;
import springsystem.BeanNotFoundException;
import springsystem.MyInjector;

import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, BeanNotFoundException {

        System.out.println("This is MySpring");

        MyInjector injector = new MyInjector();

        ClassB c1 = (ClassB)injector.getMyBean(ClassB.class);
        c1.result();

        ClassA c2 = (ClassA)injector.getMyBean(ClassA.class);
        c2.result();


    }
}
