package com.hsx.mybatis.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.hsx.mybatis.po.User;
import com.hsx.mybatis.po.UserCustom;
import com.hsx.mybatis.po.UserQueryVo;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	/**
	 * 创建sqlSessionFactory
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		// mybatis全局配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	/**
	 * 根据用户的Id查询
	 * 
	 * @throws Exception
	 */
	// @Test
	public void findUserByIdTest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建UserMapper接口，mybatis自动生成的mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 调用userMapper
		User user = userMapper.findUserById(1);
		sqlSession.close();
		System.out.println(user);
	}

	/**
	 * 用户信息的综合查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void findUserListTest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserCustom userCustom = new UserCustom();
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(28);
		ids.add(30);
		ids.add(32);
		ids.add(34);
		userCustom.setSex("2");
		userCustom.setUsername("李小军");
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);
		userQueryVo.setIds(ids);
		List<UserCustom> userCustoms = userMapper.findUserList(userQueryVo);
		sqlSession.close();
		for (UserCustom userCustom2 : userCustoms) {
			System.out.println(userCustom2.getUsername() + "   " + userCustom2);
		}
	}
	
	/**
	 * 用户信息的综合查询总数
	 * @throws Exception
	 */
	//@Test
	public void findUserCount() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("2");
		userCustom.setUsername("李小军");
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);
		int count = userMapper.findUserCount(userQueryVo);
		sqlSession.close();
		System.out.println("记录的总数： " + count);
	}
	
	/**
	 * 根据用户的Id查询用户[用resultMap进行输出映射]
	 * @throws Exception
	 */
	//@Test
	public void findUserByIdResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserByIdResultMap(1);
		sqlSession.close();
		System.out.println(user.getUsername() + "   " + user);
	}
	
	/**
	 * 测试一级缓存
	 * @throws Exception
	 */
	@Test
	public void testCache1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		//创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 第一次发起请求，查询id为1的用户
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		//userMapper.updateUser(user1);
		//sqlSession.commit();
		// 第二次发起请求，查询id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		sqlSession.close();
	}
	
	/**
	 * 测试二级缓存
	 * @throws Exception
	 */
	@Test
	public void testCache2() throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		
		UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
		User user1 = mapper1.findUserById(1);
		System.out.println(user1);
		sqlSession1.close(); //注意：这里执行的关闭操作，才将sqlSession中数据写到二级缓存中
		
		UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
		User user = mapper3.findUserById(1);
		user.setUsername("笑话");
		mapper3.updateUser(user);
		sqlSession3.commit();
		sqlSession3.close();
		
		
		UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = mapper2.findUserById(1);
		System.out.println(user2);
		sqlSession2.close();
		
		
	}

}
