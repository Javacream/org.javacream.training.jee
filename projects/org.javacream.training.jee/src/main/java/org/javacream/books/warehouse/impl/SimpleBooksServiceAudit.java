package org.javacream.books.warehouse.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.javacream.books.warehouse.event.BookEvent;
import org.javacream.util.CrudEventQualifiers;

@ApplicationScoped
public class SimpleBooksServiceAudit {

	public void handleBookCreated(@Observes @CrudEventQualifiers.CREATED BookEvent bookEvent) {
		System.out.println("CREATED BOOK " + bookEvent);
	}
	public void handleBookRead(@Observes @CrudEventQualifiers.READ BookEvent bookEvent) {
		System.out.println("READ BOOK " + bookEvent);
	}
	public void handleBookUpdate(@Observes @CrudEventQualifiers.UPDATED BookEvent bookEvent) {
		System.out.println("UPDATED BOOK " + bookEvent);
	}
	public void handleBookDeleted(@Observes @CrudEventQualifiers.DELETED BookEvent bookEvent) {
		System.out.println("DELETED BOOK " + bookEvent);
	}
}
