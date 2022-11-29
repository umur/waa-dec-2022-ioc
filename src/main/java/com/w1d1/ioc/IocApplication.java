package com.w1d1.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IocApplication {
    public static void main(String[] args) {
        var injector = new MyInjector();
        try {
            var instanceOfA = injector.getBean(ClassA.class);
            var instanceOfB = injector.getBean(ClassB.class);
            var instanceOfC = injector.getBean(ClassC.class);
            System.out.println(instanceOfA.toString());
            System.out.println(instanceOfB.toString());
            System.out.println(instanceOfC.toString());
        } catch (BeanNotFoundException e) {
            throw new RuntimeException(e);
        }
        SpringApplication.run(IocApplication.class, args);
    }

}
