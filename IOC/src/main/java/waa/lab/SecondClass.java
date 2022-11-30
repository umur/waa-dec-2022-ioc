package waa.lab;

@MyBean
public class SecondClass {
    @MyAutowired
    private ThirdClass thirdClass;

    public SecondClass() {
        System.out.println("2nd Class constr");
    }
    @Override
    public String toString() {
        return "SecondClass{" +
              //  "thirdClass=" + thirdClass.toString() +
                '}';
    }
}
