package com.miu.ea;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IocApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MyInjector injector = new MyInjector();
		String curPackageName = injector.getClass().getPackageName();
		injector.createObjects(curPackageName);
		ClassA bean = (ClassA) injector.getBean(Class.forName("com.miu.waa.ClassA"));
		bean.print();
	}
}
