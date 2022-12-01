import application.ClassA;
import application.ClassC;
import framework.BeanNotFoundException;
import framework.MyInjector;

public class Main {
    public static void main(String[] args) throws BeanNotFoundException, IllegalAccessException {

        //invoke injector
        MyInjector injector = new MyInjector();
        injector.CreateContainer("application");

        //application code
        ClassA a = (ClassA)injector.getBean(ClassA.class);
        a.message();

        ClassC b = (ClassC)injector.getBean(ClassC.class);
        b.message();
    }
}