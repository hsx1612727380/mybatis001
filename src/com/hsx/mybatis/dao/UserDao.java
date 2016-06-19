package com.hsx.mybatis.dao;

import com.hsx.mybatis.po.User;

public interface UserDao {
	
	public User findUserById(int id) throws Exception;
	
	public void insertUser(User user) throws Exception;
	
	public void deleteUserById(int id) throws Exception;
	
	public void updateUserById(int id) throws Exception;

}
