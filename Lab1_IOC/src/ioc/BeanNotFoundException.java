package ioc;

public class BeanNotFoundException extends RuntimeException{
    public BeanNotFoundException(String msg){
        super(msg);
    }
}
