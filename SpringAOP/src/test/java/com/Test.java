package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.interfaces.HelloWorld;

public class Test {
	public static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static void main(String[] args) {

		HelloWorld hw1 = (HelloWorld) ctx.getBean("helloWorldImpl1");
		
		hw1.getName("2");
	}

}
