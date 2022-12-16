package src.exceptionhandler;

public class BeanNotFoundException extends Exception{
    public BeanNotFoundException()
    {
        super();
    }
    public BeanNotFoundException(String msg)
    {
        super(msg);
    }

    public BeanNotFoundException(Throwable t)
    {
        super(t);
    }
}
