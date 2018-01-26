package com.wen.bean;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestUser {

	public static void main(String[] args) {
		
		String res = "conf.xml";
		
		InputStream is = TestUser.class.getClassLoader().getResourceAsStream(res);
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		User user = session.selectOne("com.wen.bean.userMapper.getUser", 1);
		
		System.out.println(user);

	}

}
