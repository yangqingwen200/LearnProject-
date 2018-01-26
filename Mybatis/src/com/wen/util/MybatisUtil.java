package com.wen.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.wen.bean.TestUser;

public class MybatisUtil {

	/**
	 * 获得一个SqlSessionFactory对象
	 * @return
	 * Author: wenwen
	 * Time: 2015年6月27日 下午3:26:53
	 */
	public static SqlSessionFactory getSessionFactoty() {
		String res = "conf.xml";

		InputStream is = TestUser.class.getClassLoader().getResourceAsStream(
				res);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		
		return sqlSessionFactory;
	}

}
