package ioc;
@MyBean
public class ClassA {
    @MyAutowired
    public ClassB b;

    ClassA(){
        System.out.println("ClassA Initialized");
    }

    public void print(){
        System.out.println("Printing from A");
    }
}
