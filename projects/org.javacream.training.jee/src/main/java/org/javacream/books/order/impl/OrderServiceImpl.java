package org.javacream.books.order.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.Order.OrderStatus;
import org.javacream.books.order.api.OrderService;
import org.javacream.books.order.api.OrderService.InMemoryStrategy;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.api.StoreService.SimpleStrategy;
import org.javacream.util.SequenceIdGenerator;

@ApplicationScoped
@InMemoryStrategy
public class OrderServiceImpl implements OrderService {

	@Inject @org.javacream.books.warehouse.api.BooksService.InMemoryStrategy 
	private BooksService booksService;
	@Inject @SimpleStrategy
	private StoreService storeService;
	@Inject @Named("forOrderService")
	SequenceIdGenerator idGenerator;
	@Inject @Named("forOrderService")
	private Map<Long, Order> orders;

	@Override
	public Order order(String isbn, int number) {
		OrderStatus orderStatus;
		double totalPrice = 0;
		try {
			Book book = booksService.findBookByIsbn((isbn));
			totalPrice = number * book.getPrice();
			if (storeService.getStock("books", isbn) >= number) {
				orderStatus = OrderStatus.OK;
			} else {
				orderStatus = OrderStatus.PENDING;
			}
		} catch (BookException e) {
			orderStatus = OrderStatus.UNKNOWN;
		}
		Order newOrder = new Order(idGenerator.next(), isbn, number, totalPrice, orderStatus);
		orders.put(newOrder.getOrderId(), newOrder);
		return newOrder;
	}

	@Override
	public List<Order> allOrders() {
		Collection<Order> values = orders.values();
		return new ArrayList<Order>(values);
	}

}
