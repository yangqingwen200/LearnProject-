package com.wen.bean.sessioncache;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;

/**
 * 测试一级缓存(session)无法使用的情况
 * @author wenwen
 * 
 * 2015年6月27日 下午3:57:31
 */
public class TestSessionCache {
	
	/**
	 *  测试使用查询一级缓存,有无使用缓存,直接看打印sql语句就行.
	 * Author: wenwen
	 * Time: 2015年6月27日 下午3:47:51
	 */
	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		User u = session.selectOne("com.wen.bean.sessioncache.userMapper.getUser", 1);
		System.out.println(u);
		
		/*
		//1.调用了clearCache方法,无法使用查询缓存
		session.clearCache();
		*/
		
		/*
		//2. 使用了更新/插入/删除方式.无法使用缓存
		session.update("com.wen.bean.cache.userMapper.update", new User(1, "aa", 20));
		session.commit();
		*/
		
		/*
		//3.不是同一个session对象,不会使用查询缓存
		session.close();
		session = sessionFactory.openSession();//重新再打开一个session
		*/
		
		User u2 = session.selectOne("com.wen.bean.sessioncache.userMapper.getUser", 1);
		System.out.println(u2);
		session.close();
	}

}
