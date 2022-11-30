package waa.lab;

@MyBean
public class ThirdClass {

    public ThirdClass() {
        System.out.println("3rd Class constr");
    }

    @Override
    public String toString() {
        return "third Class is running";
    }
}
