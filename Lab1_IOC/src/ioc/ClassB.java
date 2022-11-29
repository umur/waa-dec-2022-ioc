package ioc;
@MyBean
public class ClassB {
    @MyAutowired
    public ClassC c;

    ClassB(){
        System.out.println("ClassB Initialized");
    }

    public void print(){
        System.out.println("Printing from B");
    }
}
