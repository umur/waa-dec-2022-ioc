package com.waalab1.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IocApplication {

	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);
		MyInjector injector = new MyInjector();
		try {
			var instanceOfAClass = injector.getBean(AClass.class);
//			var instanceOfBClass = injector.getBean(BClass.class);
//			var instanceOfCClass = injector.getBean(CClass.class);
			System.out.println(instanceOfAClass.toString());
//			System.out.println(instanceOfBClass.toString());
//			System.out.println(instanceOfCClass.toString());
		} catch (BeanNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
