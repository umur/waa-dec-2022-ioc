import annotations.MyInjector;
import classes.ClassA;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        MyInjector main = new MyInjector();
        System.out.println("MyAutowired custom annotation!");
        main.getReflection();
        ClassA classA = (ClassA) main.getBean(ClassA.class);
        classA.display();
    }
}
