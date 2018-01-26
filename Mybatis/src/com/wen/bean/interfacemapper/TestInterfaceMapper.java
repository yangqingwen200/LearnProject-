package com.wen.bean.interfacemapper;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;

public class TestInterfaceMapper {

	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		UserMapper mapper = session.getMapper(UserMapper.class);
		com.wen.bean.interfacemapper.User user = mapper.getUser(1);
		session.commit();

		System.out.println(user);

		session.close();
	}
	
	@Test
	public void testGetSessionCache() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.getUser(1);
		System.out.println(user);
		
		User user2 = mapper.getUser(1);
		System.out.println(user2);
		
		session.commit();
		session.close();
	}

	@Test
	public void testSelectbyName() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> userByName = mapper.getUserByName("%e%");
		session.commit();

		for (User user : userByName) {
			System.out.println(user);
		}

		session.close();
	}

	@Test
	public void testGetUserByIdReturnMap() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		UserMapper mapper = session.getMapper(UserMapper.class);
		Map<String, Object> userByIdReturnMap = mapper.getUserByIdReturnMap(1);
		Set<String> strings = userByIdReturnMap.keySet(); // 取得所有key,按照升序排列.
		Iterator<String> iterator = strings.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next(); // key
			System.out.println("key: " + next + ", value: " + userByIdReturnMap.get(next));
		}
		session.commit();

		session.close();
	}
	
	@Test
	public void getUserByIdByNameReturnMap() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		Map<Integer, User> userByIdByNameReturnMap = mapper.getUserByIdByNameReturnMap("%e%");
		
		session.commit();
		Set<Integer> strings = userByIdByNameReturnMap.keySet(); // 取得所有key,按照升序排列.
		Iterator<Integer> iterator = strings.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next(); // key
			System.out.println("key: " + next + ", value: " + userByIdByNameReturnMap.get(next));
		}

		session.close();
	}

	@Test
	public void testInsert() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		UserMapper mapper = session.getMapper(UserMapper.class);
		Integer insert = mapper.insert("yang", 20, null);
		session.commit();

		System.out.println(insert);

		session.close();
	}

}
