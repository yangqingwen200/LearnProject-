package com.wen.bean.dynamic;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;

public class TestDynamicMapper {

	@Test
	public void getUserWhere() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		UserDynamicMapper mapper = session.getMapper(UserDynamicMapper.class);
		User user = new User();
		//user.setName("%y%");
		user.setAge(20);
		List<User> userByName = mapper.getUserWhere(user);
		for (User user2 : userByName) {
			System.out.println(user2);
		}
		
		session.commit();
		session.close();
	}
	
	@Test
	public void getUserSqlInclude() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserDynamicMapper mapper = session.getMapper(UserDynamicMapper.class);
		User user = new User();
		List<User> userByName = mapper.getUserSqlInclude(user);
		for (User user2 : userByName) {
			System.out.println(user2);
		}
		
		session.commit();
		session.close();
	}
	
	@Test
	public void getUserTrim() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserDynamicMapper mapper = session.getMapper(UserDynamicMapper.class);
		User user = new User();
		//user.setName("%y%");
		user.setAge(20);
		List<User> userByName = mapper.getUserTrim(user);
		for (User user2 : userByName) {
			System.out.println(user2);
		}
		
		session.commit();
		session.close();
	}
	
	@Test
	public void getUserChoose() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserDynamicMapper mapper = session.getMapper(UserDynamicMapper.class);
		User user = new User();
		/*user.setId(1);
		user.setName("%y%");*/
		List<User> userByName = mapper.getUserChoose(user);
		for (User user2 : userByName) {
			System.out.println(user2);
		}
		
		session.commit();
		session.close();
	}
	
	@Test
	public void updateUser() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserDynamicMapper mapper = session.getMapper(UserDynamicMapper.class);
		User user = new User();
		user.setId(1);
		user.setLastName("man");
		//user.setName("qing");
		Integer updateUser = mapper.updateUser(user);
		System.out.println(updateUser);
		session.commit();
		session.close();
	}
	
	@Test
	public void getUserForeach() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserDynamicMapper mapper = session.getMapper(UserDynamicMapper.class);
		List<User> userForeach = mapper.getUserForeach(Arrays.asList(1));
		for (User user : userForeach) {
			System.out.println(user);
		}
		session.commit();
		session.close();
	}

}
