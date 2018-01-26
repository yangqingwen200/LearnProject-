package com.wen.bean.interfacemapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	
	public User getUser(int id);
	
	public Integer insert(@Param("name")String name, @Param("age")int age, @Param("lastName")String lastName);
	
	public List<User> getUserByName(String name);
	
	public Map<String, Object> getUserByIdReturnMap(Integer id);
	
	@MapKey(value="id")
	public Map<Integer, User> getUserByIdByNameReturnMap(String name);

}
