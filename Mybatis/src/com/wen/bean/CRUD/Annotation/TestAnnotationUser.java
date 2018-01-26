package com.wen.bean.CRUD.Annotation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;

public class TestAnnotationUser {

	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		UserMapper um = session.getMapper(UserMapper.class);
		User u = um.getUser(1);
		session.commit();
		System.out.println(u);
		session.close();
	}

	@Test
	public void testAllGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserMapper um = session.getMapper(UserMapper.class);
		List<User> users = um.getAllUser();
		System.out.println(users);
		
		session.close();
	}

	@Test
	public void testAdd() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserMapper um = session.getMapper(UserMapper.class);
		int result = um.add(new User(-1, "aaaaa", 636));
		
		session.commit();
		System.out.println(result);
		session.close();
	}

	@Test
	public void testUpdate() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		UserMapper um = session.getMapper(UserMapper.class);

		int result = um.update(new User(1, "afdasfds", 123));
		session.commit();
		System.out.println(result);
		session.close();
	}

	@Test
	public void testDelete() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserMapper um = session.getMapper(UserMapper.class);
		int result = um.delete(8);
		session.commit();
		System.out.println(result);
		session.close();
	}

}
