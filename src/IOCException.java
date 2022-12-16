package src;
public class IOCException extends  Exception {
    public IOCException(){
        super();
    }

    public IOCException(String msg){
        super(msg);
    }

    public IOCException(Throwable t){
        super(t);
    }
}
