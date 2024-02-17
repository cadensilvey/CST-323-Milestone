package com.gcu.business;

import java.util.List;

import com.gcu.model.OrderModel;

public interface OrdersBusinessServiceInterface {
	public void test();
	public List<OrderModel> getOrders();
	public void init();
	public void destroy();
	public OrderModel getById(int id);
	public List<OrderModel> searchOrders(String searchTerm);
	public long addOne(OrderModel newOrder);
	public boolean deleteOne(long id);
	public OrderModel updateOne(long idToUpdate, OrderModel updateOrder);
}
