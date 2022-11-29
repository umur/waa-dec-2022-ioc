import ioc.ClassA;
import ioc.ClassB;
import ioc.ClassC;
import ioc.MyInjector;

public class Main {
    public static void main(String[] args) {
        MyInjector inj = new MyInjector();
        inj.getBean(ClassA.class).print();
        inj.getBean(ClassB.class).print();
        inj.getBean(ClassC.class).print();
        System.out.println("Complete");
    }
}