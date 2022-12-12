package com.example.waadecioc;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyAutowired {
    boolean required() default true;
}

