package com.wen.bean.onetomany;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wen.util.MybatisUtil;

public class TestTeacherClass {

	@Test
	public void testGet() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		Class u = session.selectOne("com.wen.bean.onetomany.teacherclassMapper.getClassTeacher",
				1);
		session.commit();
		
		Teacher teacher = u.getTeacher();
		System.out.println(teacher);
		
		List<Student> stus = u.getStudents();
		for (Student student : stus) {
			System.out.println(student);
		}
		System.out.println(u);
		session.close();
	}
	
	@Test
	public void testGet2() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		Class u = session.selectOne("com.wen.bean.onetomany.teacherclassMapper.getClass",
				1);
		session.commit();
		
		Teacher teacher = u.getTeacher();
		System.out.println(teacher);
		
		List<Student> stus = u.getStudents();
		for (Student student : stus) {
			System.out.println(student);
		}
		System.out.println(u);
		session.close();
	}

}
