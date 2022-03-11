package org.javacream.books.order.impl;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.Order.OrderStatus;
import org.javacream.util.Dev;
import org.javacream.util.Prod;

public class OrderServiceConfiguration {

	
	@Produces @Prod @Named("forOrderService") public Map<Long, Order> booksMapForProd(){
		return new HashMap<Long, Order>();
	}
	@Produces @Dev  @Named("forOrderService") public Map<Long, Order> booksMapForDev(){
		HashMap<Long, Order> data = new HashMap<Long, Order>();
		Order o = new Order(42l, "ISBN42", 42, 99.99, OrderStatus.OK);
		data.put(o.getOrderId(), o);
		return data;
	}
}
