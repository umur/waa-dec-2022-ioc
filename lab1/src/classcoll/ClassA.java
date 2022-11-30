package classcoll;

@MyBean
public class ClassA {

    @MyAutowired
    private ClassB b;

    public void result(){
        b.result();
        System.out.println("This is Class A");
    }
}
