package com.wen.bean.CRUD;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;


public class TestUser {
	
	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		User u = session.selectOne("com.wen.bean.CRUD.userMapper.getUser", 1);
		session.commit();
		System.out.println(u);
		session.close();
	}
	
	@Test
	public void testAllGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		List<User> user = session.selectList("com.wen.bean.CRUD.userMapper.getAllUser");
		
		session.commit();
		System.out.println(user);
		session.close();
	}

	@Test
	public void testAdd() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		//需要手动提交,如果自动提交的话,用sessionFactory.openSession(true)
		SqlSession session = sessionFactory.openSession();
		
		int result = session.insert("com.wen.bean.CRUD.userMapper.add", new User(11, "manman", 20));
		session.commit();
		System.out.println(result);
		session.close();
	}
	
	@Test
	public void testUpdate() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		//如果没有更新数据.就返回0,有数据被更新的话,就返回1
		int result = session.update("com.wen.bean.CRUD.userMapper.update", new User(10, "xiaogui", 30));
		session.commit();
		System.out.println(result);
		session.close();
	}
	
	@Test
	public void testDelete() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		//如果有记录被删除,就返回1,没有记录被删除的话,就返回0
		int result = session.delete("com.wen.bean.CRUD.userMapper.delete", 11);
		session.commit();
		System.out.println(result);
		session.close();
	}

}
