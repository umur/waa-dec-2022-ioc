import IocImplementation.*;
import MyExceptions.BeanNotFoundException;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MyInjector injector = new MyInjector();

        List<Class> clazzez = Arrays.asList(ClassA.class, ClassB.class, ClassC.class, ClassD.class);

        for (Class c : clazzez) {
            try {
                if (injector.getBean(c) != null) {
                    System.out.println("Object found for '" + injector.getBean(c).getClass().getName() + "'");
                }
            } catch (BeanNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}