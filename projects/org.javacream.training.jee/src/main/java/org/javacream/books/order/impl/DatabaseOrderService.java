package org.javacream.books.order.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.Order.OrderStatus;
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
		return newOrder;
	}

	@Override
	public List<Order> allOrders() {
		return entityManager.createQuery("select o from Order as o", Order.class).getResultList();
	}

}
