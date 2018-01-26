package com.wen.bean.procedure;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;


public class TestUser {
	
	/**
	 * 调用存储过程,查询数据
	 * Author: wenwen
	 * Time: 2015年6月27日 下午3:18:35
	 */
	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		//在uesrMapper.xml有对parammap进行输入和输入的参数的配置
		Map<String, Integer> parammap = new HashMap<String, Integer>();
		parammap.put("age", 1); //age作为输入的参数
		parammap.put("count", -1); //count作为保存结果的输出参数,value为其他值,这里为-1
		
		session.selectOne("com.wen.bean.procedure.userMapper.getCallProcedure", parammap);
		
		int result = parammap.get("count");
		session.commit();
		System.out.println(result);
		session.close();
	}
}
	
