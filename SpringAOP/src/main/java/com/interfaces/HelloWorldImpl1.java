package com.interfaces;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldImpl1 implements HelloWorld {
	
	@Override
    public void printHelloWorld()
    {
        System.out.println("Enter HelloWorldImpl1.printHelloWorld()");
    }
    
    @Override
    public void doPrint()
    {
        System.out.println("Enter HelloWorldImpl1.doPrint()");
        return ;
    }

	@Override
	public String getName(String id) {
		return "getName: " + id + " ";
	}
}
