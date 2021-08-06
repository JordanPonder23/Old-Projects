package com.example;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.VillainService;
import com.example.service.VillainServiceImpl;

/*
 * WHAT IS SPRING??????
 * 	Spring is a module based, open source, java AOP framework. It provides support
 * 	for enterprise level applications; it provides infrastructure so you
 * 	can focus on your application's business logic. (AOP is Aspect Oriented
 * 	Programming)
 * 
 * What is a module?
 * 	A grouping of libraries that work together to achieve some goal using
 * 	reusable abstracted code. In spring, it's a spring dependency.
 * 
 * What are some modules in spring?
 * 	core, beans, context, orm, aop, webmvc, security, test
 * 
 * How does spring achieve its goals? Aka what features does spring have?
 * 		Inversion of Control (IoC)
 * 		Aspect Oriented Programming (AOP)
 * 		Model View Controller (MVC)
 * 		Abstraction API
 * 
 * What is bean wiring?
 * 	Creating an association between spring's bean container and an object you
 * 	want spring to manage. AS WELL AS defining which beans are dependencies of
 * 	others.
 * 
 * Benefits of Dependency Injection (DI)?
 * 	It decouples our code. Cleaner code. Testability. Maintainability.
 * 	Scalability. Reduces complexity.
 * 
 * Scopes of a spring bean:
 * 	Singleton (default)
 * 	Prototype
 * 	Request
 * 	Session
 * 	GlobalSession
 * 
 * What is applicationContext?
 * 	Application Context is a BeanFactory. It inherits from the BeanFactory
 * 	interface. It adds more functionality: for example, internalization, text
 * 	messaging.
 */

public class Driver {
	//creating a DAO instance without DI, THIS IS THE OLD WAY OF DOING THINGS
	//private static VillainService villServ = new VillainServiceImpl(new VillainDaoImpl());

	//with DI
	private static VillainService villServ;
	
	public static void main(String[] args) {
		//THIS line starts spring up, the IoC container is starting here
		ApplicationContext appContext =
				new ClassPathXmlApplicationContext("beans.xml");
		
		villServ = appContext.getBean("villServ009", VillainService.class);
		
		//This is a demo to test the counter static variable's functionality
		/*new VillainServiceImpl();
		new VillainServiceImpl();
		new VillainServiceImpl();*/
		
		//this is a demo to test spring singleton/prototype scope(s)
		/*appContext.getBean("villServ009", VillainService.class);
		appContext.getBean("villServ009", VillainService.class);
		appContext.getBean("villServ009", VillainService.class);*/
		
		cleanPrint(villServ.getAllVills());
		System.out.println("The counter is at: "+VillainServiceImpl.counter);
	}
	
	//This method makes a printed list more readable in the console
	public static <T> void cleanPrint(List<T> myList) {
		System.out.println("The List: ");
		
		for (T listItem : myList) {
			System.out.println("  "+listItem);
		}
	}

}
