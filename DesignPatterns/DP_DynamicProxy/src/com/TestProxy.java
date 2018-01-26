package com;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestProxy {
	public static void main(String[] args) {
		
		// ����Ҫ�������ʵ����
		Subject realSubject = new RealSubject();

		// ����Ҫ�����ĸ���ʵ���󣬾ͽ��ö��󴫽�ȥ�������ͨ������ʵ�����������䷽����
		InvocationHandler handler = new DynamicProxy(realSubject);

		/*
		 * ͨ��Proxy��newProxyInstance�������������ǵĴ�������������������������� ��һ������
		 * handler.getClass().getClassLoader()
		 * ����������ʹ��handler������ClassLoader�������������ǵĴ������
		 * �ڶ�������realSubject.getClass().getInterfaces()����������Ϊ��������ṩ�Ľӿ�����ʵ������ʵ�еĽӿ�
		 * ����ʾ��Ҫ������Ǹ���ʵ���������Ҿ��ܵ�������ӿ��еķ����� ����������handler�� �������ｫ������������������Ϸ���
		 * InvocationHandler ���������
		 */
		Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
/*
		subject.rent();
		subject.hello("world");*/
		String name = subject.getName(1);
		System.out.println(name);
	}
}