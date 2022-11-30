import application.First;
import application.Second;
import contatiner.BeanNotFoundException;
import contatiner.MyInjector;

public class Main {
    public static void main(String[] args) throws BeanNotFoundException, IllegalAccessException{

        MyInjector injector = new MyInjector();
        injector.CreateContainer("application");

        First f1 =(First)injector.getBean(First.class);
        f1.print();

        Second s1 = (Second)injector.getBean(Second.class);
        s1.print();
    }
}