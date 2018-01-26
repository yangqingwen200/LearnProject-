package com.wen.bean.CRUD.Annotation;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 采用注解的形式
 * @author wenwen
 * 
 * 2015年6月27日 上午1:27:31
 */
public interface UserMapper {
	
	@Select("select * from user where id=#{id}")
	public User getUser(int id);
	
	@Update("update user set name=#{name}, age=#{age} where id=#{id}")
	public int update(User user);
	
	@Select("select * from user")
	public List<User> getAllUser();
	
	@Delete("delete from user where id = #{id}")
	public int delete(int id);
	
	@Insert("insert into user (name, age) value (#{name}, #{age})")
	public int add(User use);

}
