package org.javacream.book.demo;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import org.javacream.books.event.BookEvent;

@ApplicationScoped
public class AllBookEventsConsumer {
	public void handleBookDeletion(@Observes @BookEvent.Deleted BookEvent event) {
		System.out.println("AllBookEventsConsumer: detected book deletion for isbn=" + event.getIsbn());
	}
	public void handleBookUpate(@Observes @BookEvent.Updated BookEvent event) {
		System.out.println("AllBookEventsConsumer: detected book update for isbn=" + event.getIsbn());
	}
	public void handleBookCreation(@Observes @BookEvent.Created BookEvent event) {
		System.out.println("AllBookEventsConsumer: detected book creation for isbn=" + event.getIsbn());
	}
}
