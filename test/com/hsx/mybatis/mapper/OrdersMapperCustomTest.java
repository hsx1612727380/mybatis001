package com.hsx.mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.hsx.mybatis.po.Orders;
import com.hsx.mybatis.po.OrdersCustom;
import com.hsx.mybatis.po.User;

public class OrdersMapperCustomTest {
	
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 创建会话工厂sqlSessionFactory
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void findOrdersUserTest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
		List<OrdersCustom> list = mapper.findOrdersUser();
		sqlSession.close();
		System.out.println(list);
		for (OrdersCustom ordersCustom : list) {
			System.out.println("list " + ordersCustom + ordersCustom.getUsername() + ordersCustom.getId() + ordersCustom.getUserId());
		}
	}
	
	@Test
	public void findOrdersUserResultMapTest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> list = mapper.findOrdersUserResultMap();
		sqlSession.close();
		System.out.println(list);
		for (Orders orders: list) {
			System.out.println("list " + orders + orders.getUser().getUsername() + orders.getId() + orders.getUserId());
		}
	}
	
	@Test
	public void findOrdersAndOrderDetialResultMapTest() throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> list= mapper.findOrdersAndOrderDetialResultMap();
		sqlSession.close();
		System.out.println(list);
		for (Orders orders : list) {
			System.out.println("list " + orders + orders.getUser().getUsername() + orders.getId() + orders.getUserId() + orders.getOrderDetials());
		}
	}
	
	@Test
	public void findUserAndItemsResultMapTest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
		List<User> users = mapper.findUserAndItemsResultMap();
		sqlSession.close();
		System.out.println(users);
	}
	
	@Test
	public void findOrderAndUserByLazyingTest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> orders = mapper.findOrderAndUserByLazying();
		sqlSession.close();
		for (Orders o : orders) {
			System.out.println(o);
			User user = o.getUser(); // 这句话就是按需加载,才会用到懒加载
			System.out.println(user);
		}
	}
	
}
