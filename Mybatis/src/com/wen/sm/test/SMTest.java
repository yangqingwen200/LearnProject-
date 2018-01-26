package com.wen.sm.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wen.sm.service.UserService;

public class SMTest {
	
    private static UserService us;
    private static ApplicationContext ctx;
 
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
         ctx = new ClassPathXmlApplicationContext("beans.xml");
         us=(UserService) ctx.getBean("service");
    }
 
    @Test
    public void testSave() {
    	us.save();
    }

}
