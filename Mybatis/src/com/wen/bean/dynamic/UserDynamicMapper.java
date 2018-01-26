package com.wen.bean.dynamic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserDynamicMapper {
	
	public List<User> getUserWhere(User user);
	
	public List<User> getUserTrim(User user);
	
	public List<User> getUserChoose(User user);
	
	public Integer updateUser(User user);
	
	public List<User> getUserForeach(@Param("ids")List<Integer> ids);
	
	public List<User> getUserSqlInclude(User user);

}
