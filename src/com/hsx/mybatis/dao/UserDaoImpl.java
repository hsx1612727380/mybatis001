package com.hsx.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hsx.mybatis.po.User;

public class UserDaoImpl implements UserDao {
	
	// 需要向dao实现类中注入SqlSessionFactory
	// 这里通过构造函数注入
	private SqlSessionFactory sqlSessionFactory;
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public User findUserById(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("test.findUserById", id);
		sqlSession.close();
		return user;
	}

	@Override
	public void insertUser(User user) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("test.insertUser", user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void deleteUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
