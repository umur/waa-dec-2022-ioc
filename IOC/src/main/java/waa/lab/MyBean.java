package waa.lab;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyBean {

//    String initMethod() default "";
//    String deleteMethod() default "1";
//    boolean preAutowired() default true;

}
