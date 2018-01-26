package com.wen.bean.fuzzyquery;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;


public class TestFuzzyUser {
	
	/**
	 * 查询user表中name中含有"g",年龄在20到30岁之间
	 * Author: wenwen
	 * Time: 2015年6月27日 下午1:59:13
	 */
	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		String name = "g";
		
		Condition conditionQuery = new Condition("%" + name + "%", 20, 30);
		List<User> u = session.selectList("com.wen.bean.fuzzyquery.userMapper.getQueryUser", conditionQuery );
		
		session.commit();
		System.out.println(u);
		session.close();
	}
	
	@Test
	public void testGet2() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		//注意: 当name为null的时候 ,"%" + name + "%"就成了%null%.
		//所以在jsp前台界面传值的时候,一定要注意赋值过来..
		
		//解决方案: 在userMapper有.
		String name = null;
		
		Condition conditionQuery = new Condition("%" + name + "%", 20, 30);
		List<User> u = session.selectList("com.wen.bean.fuzzyquery.userMapper.getQueryNullUser", conditionQuery );
		
		session.commit();
		System.out.println(u);
		session.close();
	}
	
}
