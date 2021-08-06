package com.example.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {

	public static void main(String[] args) {

		ApplicationContext appCon =
				new ClassPathXmlApplicationContext("trevin.xml");
		
		MyAppProxy app = appCon.getBean("appProxy", MyAppProxy.class);
		
		app.drawCartoon();
		app.drawAnime(0);
		app.drawTheBestAnime(0, 0);
		app.drawNature();
		app.sculptPottery();
	}

}
