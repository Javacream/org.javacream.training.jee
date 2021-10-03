package org.javacream.books.order.impl;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.Order.OrderStatus;
import org.javacream.books.order.event.OrderEvent;
import org.javacream.books.order.api.OrderService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.javacream.util.SequenceIdGenerator;

@ApplicationScoped
@org.javacream.books.order.api.OrderService.DatabaseStrategy
@Transactional
public class DatabaseOrderService implements OrderService {

	@Inject
	@org.javacream.books.warehouse.api.BooksService.DatabaseStrategy
	private BooksService booksService;
	@Inject
	@org.javacream.store.api.StoreService.DatabaseStrategy
	private StoreService storeService;
	@Inject
	@Named("forOrderService")
	SequenceIdGenerator idGenerator;
	@PersistenceContext
	private EntityManager entityManager;

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
		entityManager.persist(newOrder);
		orderProducer.fire(new OrderEvent(newOrder.getOrderId(), isbn, number, orderStatus == OrderStatus.OK));
		return newOrder;
	}

	@Override
	public List<Order> allOrders() {
		return entityManager.createQuery("select o from Order as o", Order.class).getResultList();
	}
	
	@Inject private Event<OrderEvent> orderProducer;

}
