package com.hsx.mybatis.dao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.hsx.mybatis.dao.UserDao;
import com.hsx.mybatis.dao.UserDaoImpl;

public class UserDaoTest {
	
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 创建SqlSessionFactory
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		// mybatis全局配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testUserDao() throws Exception {
		// 创建UserDao的对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		// 调用UserDao的方法
		userDao.findUserById(1);
	}
	

}
