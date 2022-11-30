package waa.lab;

@MyBean
public class FirstClass {
    @MyAutowired
    private SecondClass secondClass;


    public FirstClass() {
        System.out.println("1st Class constr");
    }

    @Override
    public String toString() {
        return "FirstClass{" +
         //       "secondClass=" + secondClass.toString() +
                '}';
    }
}
