package MyExceptions;

public class BeanNotFoundException extends Exception {

    public BeanNotFoundException() {
    }

    public BeanNotFoundException(String message) {
        super(message);
    }

    public BeanNotFoundException(Throwable cause) {
        super(cause);
    }
}
