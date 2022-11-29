package Ioc;

public class Main {
    public static void main(String[] args) {
        MyInjector myInjector= new MyInjector();
        try{
            //myInjector.getBean(ClassB.class);
           Object obj = myInjector.getBean(ClassB.class);
            System.out.println("Object Found, Can be obtained from Ioc" + obj);
        }
        catch (BeanNotFoundException exception)
        {
            System.out.println("No Object Found");
        }

    }
}
