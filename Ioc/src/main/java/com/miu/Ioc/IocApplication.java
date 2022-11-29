package com.miu.Ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IocApplication {

	public static void main(String[] args) {
		try {
			run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	public static void run() throws Exception {
		MyInjector myInjector = new MyInjector();
		String curPackageName = myInjector.getClass().getPackageName(); // "com.miu.Ioc"
		System.out.println("MyInjector creates instances for classes with annotation MyBean:");

		myInjector.createInstances(curPackageName);

		System.out.println("MyInjector createInstances finished!");
		System.out.println("------------------------------------");

		MyTestClassA objectA = null;

		try {
			objectA = (MyTestClassA) myInjector.getBean(Class.forName("com.miu.loc.MyTestClassA"));
			System.out.println("Get bean method is invoked for : MyTestClassA" + objectA);
		} catch (BeanNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("Get bean method is invoked for : MyTestClassB"
					+ myInjector.getBean(Class.forName("com.miu.waa.MyTestClassB")));
		} catch (BeanNotFoundException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("------------------------------------");
		System.out.println("ObjectA is calling method print");
		if (objectA != null) {
			objectA.print();
		} else {
			System.out.println("ObjectA is null");
		}
	}
}
