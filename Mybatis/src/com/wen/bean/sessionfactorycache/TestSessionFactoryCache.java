package com.wen.bean.sessionfactorycache;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;

/**
 * 测试二级缓存(sessionFactory)
 * @author wenwen
 * 2015年6月27日 下午3:57:31
 */
public class TestSessionFactoryCache {
	
	/**
	 *  测试使用查询二级缓存,有无使用缓存,直接看打印sql语句就行.
	 *  控制台打印出Cache Hit Ratio [com.wen.bean.sessionfactorycache.userMapper]: 0.5 ,即表示使用了sessionFactory
	 * Author: wenwen
	 * Time: 2015年6月27日 下午3:47:51
	 */
	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session1 = sessionFactory.openSession();
		SqlSession session2 = sessionFactory.openSession();
		
		User u = session1.selectOne("com.wen.bean.sessionfactorycache.userMapper.getUser", 1);
		System.out.println(u);
		session1.commit(); //必须提交.否则使用不了二级缓存..
		
		
		User u2 = session2.selectOne("com.wen.bean.sessionfactorycache.userMapper.getUser", 1);
		System.out.println(u2);
		session2.commit(); //必须提交.否则使用不了二级缓存..
		session1.close();
		session2.close();
	}

}
