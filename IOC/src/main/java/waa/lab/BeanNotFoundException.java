package waa.lab;

public class BeanNotFoundException extends Exception{
    public BeanNotFoundException(String msg, String className){
        super(msg);
    }
}
