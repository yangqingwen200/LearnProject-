package com.wen.sm.service.impl;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wen.sm.bean.User;
import com.wen.sm.mapper.UserMapper;
import com.wen.sm.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	//支持批量插入的sql session
	@Autowired
	@Qualifier(value="sqlSession")
	private SqlSession sqlSession;
	
	public void save() {
		User user = new User(-1, "tom", new Date(), 1234);
		mapper.insert(user);
		
		int id = user.getId();
		System.out.println(id);
	}

}
