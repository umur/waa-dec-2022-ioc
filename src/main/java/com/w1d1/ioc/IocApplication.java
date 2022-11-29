package com.w1d1.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IocApplication {
    public static void main(String[] args) {
        var injector = new MyInjector();
        try {
            injector.getBean(ClassA.class).toString();
            injector.getBean(ClassB.class).toString();
            injector.getBean(ClassC.class).toString();
        } catch (BeanNotFoundException e) {
            throw new RuntimeException(e);
        }
        SpringApplication.run(IocApplication.class, args);
    }

}
