package Ioc;

public class Main {
    public static void main(String[] args) {
        MyInjector myInjector= new MyInjector();
        myInjector.MyAutoWiredObjects();
        try{
            myInjector.getBean(ClassA.class);
            //myInjector.getBean(ClassC.class); This will throw Exception
            System.out.println("Object Found, Can be obtained from Ioc");
        }
        catch (BeanNotFoundException exception)
        {
            System.out.println("No Object Found");
        }

    }
}
