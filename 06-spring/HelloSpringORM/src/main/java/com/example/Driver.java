package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.FoodRepo;
import com.example.model.Food;

public class Driver {

	public static ApplicationContext appContext=
			new ClassPathXmlApplicationContext("applicationContext.xml");
	public static FoodRepo foodRepo= appContext.getBean("foodRepo", FoodRepo.class);
	
	public static void main(String[] args) {
		insertInitialValues();
		
		System.out.println("All our fooodsssss: " + foodRepo.selectAll());
	}
	
	public static  void insertInitialValues() {
		Food food1 = new Food("Banana", 90.0);
		foodRepo.insert(food1);
		
		Food food2 = new Food("Macaroons", 300.1);
		foodRepo.insert(food2);
		
		Food food3 = new Food("Baked MacNCheese", 1250.0);
		foodRepo.insert(food3);
		
		Food food4 = new Food("Filet Mignon", 278.0);
		foodRepo.insert(food4);
	}
	
}
