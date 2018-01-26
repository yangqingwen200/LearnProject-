package com.wen.bean.pagehelper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.util.MybatisUtil;

public class TestPagehelperMapper {

	@Test
	public void testPagehelper() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();

		UserMapper mapper = session.getMapper(UserMapper.class);
		Page<Object> startPage = PageHelper.startPage(2, 10);
		List<User> userByName = mapper.getUserByName("%e%");

		int pageNum = startPage.getPageNum();
		long total = startPage.getTotal();
		int pages = startPage.getPages();
		System.out.println("当前页: " + pageNum);
		System.out.println("总页数: " + pages);
		System.out.println("总条数: " + total);
		for (User user : userByName) {
			System.out.println(user);
		}
		session.close();
	}
	
	@Test
	public void testPagehelperInfo() {
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactoty();
		SqlSession session = sessionFactory.openSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		PageHelper.startPage(2, 10);
		List<User> userByName = mapper.getUserByName("%e%");
		PageInfo<User> page = new PageInfo<User>(userByName, 10);//10表示分页栏连续显示10个数字,点击数字跳到对应的页面
		
		System.out.println("当前页码："+page.getPageNum());
		System.out.println("总记录数："+page.getTotal());
		System.out.println("每页的记录数："+page.getPageSize());
		System.out.println("总页码："+page.getPages());
		System.out.println("是否第一页："+page.isIsFirstPage());
		System.out.println("连续显示的页码：" + page.getNavigatePages());
		int[] navigatepageNums = page.getNavigatepageNums();
		for (int i : navigatepageNums) {
			System.out.print(i);
		}
		System.out.println();
		System.out.println("以下为数据信息:");
		List<User> list = page.getList();
		for (User user : list) {
			System.out.println(user);
		}
		//查询不需要commit.
		session.close();
	}

}
