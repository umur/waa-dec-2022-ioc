package ioc;
@MyBean
public class ClassC {
    ClassC(){
        System.out.println("ClassC Initialized");
    }

    public void print(){
        System.out.println("Printing from C");
    }
}
