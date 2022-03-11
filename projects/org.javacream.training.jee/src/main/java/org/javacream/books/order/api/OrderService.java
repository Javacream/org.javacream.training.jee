package org.javacream.books.order.api;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

import javax.inject.Qualifier;

public interface OrderService {

	Order order(String isbn, int number);

	List<Order> allOrders();

	@Retention(RUNTIME)
	@Target({ TYPE, FIELD, METHOD, PARAMETER })
	@Qualifier
	public @interface InMemoryStrategy {

	}

}
