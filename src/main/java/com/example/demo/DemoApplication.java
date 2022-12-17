package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws BeanNotFoundException {
		SpringApplication.run(DemoApplication.class, args);
		MyInjector injector = new MyInjector();
		ClassA  classA1 = (ClassA) injector.getBean(ClassA.class);
		System.out.println(classA1.display());
	}

}
