package com.example.waadecioc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WaaDecIocApplication {

	public static void main(String[] args) throws BeanNotFoundException {


			SpringApplication.run(WaaDecIocApplication.class, args);
			MyInjector injector = new MyInjector();
			ClassA  classA1 = (ClassA) injector.getBean(ClassA.class);
			System.out.println(classA1.display());


	}

}
