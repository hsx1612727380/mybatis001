package com.hsx.mybatis.po;

import java.util.List;

/**
 * 用户类的包装类型
 * @author hsx
 *
 */
public class UserQueryVo {
	
	// 传入多个id
	private List<Integer> ids;
	
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	// 包装所需要的查询条件
	
	// 用户查询条件
	private UserCustom userCustom;

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	
	// 可以包装其他的查询条件，订单，商品
	
}
