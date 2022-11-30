import application.Class1;
import application.Class2;
import framework.BeanNotFoundException;
import framework.MyInjector;

public class Main {
    public static void main(String[] args) throws BeanNotFoundException, IllegalAccessException {

        //invoke injector
        MyInjector injector = new MyInjector();
        injector.CreateContainer("application");

        //application code
        Class1 c1 = (Class1)injector.getBean(Class1.class);
        c1.print();

        Class2 c2 = (Class2)injector.getBean(Class2.class);
        c2.print();
    }
}