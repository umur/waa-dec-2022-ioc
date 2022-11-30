package waa.lab;

public class Main {
    public static void main(String[] args) throws BeanNotFoundException {
        System.out.println("WAA W1D1");
        MyInjector inj = new MyInjector();
        inj.getBeans(FirstClass.class).toString();
        inj.getBeans(SecondClass.class).toString();
        inj.getBeans(ThirdClass.class).toString();
        System.out.println("Completed");
    }
}