package miu.waa;

/**
 * @author Ankhbayar Azzaya
 */
@MyBean
public class B {

    @MyAutoWired
    private C c;

    @MyAutoWired
    private D d;

    @Override
    public String toString() {
        return "B injected [" + c.toString() +  ", " + d.toString() + "]";
    }
}
