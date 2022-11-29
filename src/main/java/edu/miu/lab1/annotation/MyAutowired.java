package edu.miu.lab1.annotation;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
    boolean required() default true;
}
