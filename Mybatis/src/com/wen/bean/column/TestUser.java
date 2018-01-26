package com.wen.bean.column;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;

public class TestUser {

	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		Order u = session.selectOne("com.wen.bean.column.userMapper.getOrder", 1);
		session.commit();
		System.out.println(u);
		session.close();
	}
	
	@Test
	public void testGet2() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		Order u = session.selectOne("com.wen.bean.column.userMapper.getOrder2", 2);
		session.commit();
		System.out.println(u);
		session.close();
	}

}
