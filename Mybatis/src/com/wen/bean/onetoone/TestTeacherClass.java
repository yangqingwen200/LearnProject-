package com.wen.bean.onetoone;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;

public class TestTeacherClass {

	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		Class u = session.selectOne("com.wen.bean.onetoone.teacherclassMapper.getClassTeacher",
				1);
		session.commit();
		System.out.println(u);
		session.close();
	}
	
	@Test
	public void testGet2() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		Class u = session.selectOne("com.wen.bean.onetoone.teacherclassMapper.getClass",
				1);
		session.commit();
		System.out.println(u);
		session.close();
	}

}
