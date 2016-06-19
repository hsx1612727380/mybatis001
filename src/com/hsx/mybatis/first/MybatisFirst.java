package com.hsx.mybatis.first;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.hsx.mybatis.po.User;

public class MybatisFirst {

	/**
	 * 根据id查询用户信息，得到一条记录
	 * 
	 * @throws Exception
	 */
	// @Test
	public void findUserByidTest() throws Exception {
		// mybatis全局配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过工厂得到SqlSession
		SqlSession sqlSession = factory.openSession();
		// 通过SqlSession操作数据库
		// 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
		// 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
		User user = (User) sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user.toString());
		// 释放资源
		sqlSession.close();
	}

	/**
	 * 根据用户名称模糊查询用户信息,可能返回多条数据
	 * 
	 * @throws Exception
	 */
	// @Test
	public void findUserByUsernameTest() throws Exception {
		// mybatis全局配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过工厂得到SqlSession
		SqlSession sqlSession = factory.openSession();
		// 通过SqlSession对数据库进行操作
		sqlSession.selectList("test.findUserByUsername", "小明");
		// 释放资源
		sqlSession.close();
	}

	/**
	 * 添加用户
	 * 
	 * @throws Exception
	 */
	// @Test
	public void insertUserTest() throws Exception {
		// mybatis全局配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过工厂得到SqlSession
		SqlSession sqlSession = factory.openSession();
		// 通过SqlSession对数据库进行操作
		User user = new User();
		user.setUsername("李小军");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setAddress("安徽黄山");
		sqlSession.insert("test.insertUser", user);
		// 提交事务
		sqlSession.commit();
		System.out.println(user.getId());
		// 释放资源
		sqlSession.close();
	}

	/**
	 * 根据用户的id删除用户
	 * 
	 * @throws Exception
	 */
	//@Test
	public void deleteUserByIdTest() throws Exception {
		// mybatis全局配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过工厂得到SqlSession
		SqlSession sqlSession = factory.openSession();
		// 通过SqlSession对数据库进行操作
		sqlSession.delete("test.deleteUserById", 34);
		// 提交事务
		sqlSession.commit();
		// 释放资源
		sqlSession.close();
	}

	/**
	 * 根据用户的id更新用户
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateUserByIdTest() throws Exception {
		// mybatis全局配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过工厂得到SqlSession
		SqlSession sqlSession = factory.openSession();
		// 通过SqlSession对数据库进行操作
		User user = new User();
		user.setId(35);
		user.setUsername("徐小军");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("广东广州");
		sqlSession.update("test.updateUserById", user);
		// 提交事务
		sqlSession.commit();
		// 释放资源
		sqlSession.close();
	}

}
