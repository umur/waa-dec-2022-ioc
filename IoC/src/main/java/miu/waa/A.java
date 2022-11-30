package miu.waa;

/**
 * @author Ankhbayar Azzaya
 */
@MyBean
public class A {

    @MyAutoWired
    private B b;

    @Override
    public String toString() {
        return "A injected [" + b.toString() + "]";
    }
}
