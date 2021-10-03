package org.javacream.books.order.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.javacream.books.order.event.OrderEvent;

@ApplicationScoped
public class SimpleOrderAudit {

	public void handleOrderEvent(@Observes OrderEvent event) {
		System.out.println(event);
	}

}
