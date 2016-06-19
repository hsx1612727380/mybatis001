package com.hsx.mybatis.mapper;

import java.util.List;

import com.hsx.mybatis.po.Orders;
import com.hsx.mybatis.po.OrdersCustom;
import com.hsx.mybatis.po.User;

public interface OrdersMapperCustom {
	
	/**
	 * 查询订单关联查询用户
	 * @return
	 * @throws Exception
	 */
	public List<OrdersCustom> findOrdersUser() throws Exception;
	
	/**
	 * 查询订单关联查询用户(resultMap)
	 * @return
	 * @throws Exception
	 */
	public List<Orders> findOrdersUserResultMap() throws Exception;
	
	/**
	 * 查询订单关联查询用户，及订单明细使用resultMap
	 * @return
	 * @throws Exception
	 */
	public List<Orders> findOrdersAndOrderDetialResultMap() throws Exception;
	
	/**
	 * 查询用户及购买的商品信息，使用resultMap
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserAndItemsResultMap() throws Exception;
	
	/**
	 * 查询订单信息关联用户信息，使用懒加载
	 * @return
	 * @throws Exception
	 */
	public List<Orders> findOrderAndUserByLazying() throws Exception;

}
