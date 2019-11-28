package org.javacream.books.warehouse.api;

import java.util.Collection;

import org.javacream.util.stereotype.Event;
import org.javacream.util.stereotype.EventProducer;

public interface BooksService {
	String newBook(String title) throws BookException;

	Book findBookByIsbn(String isbn) throws BookException;

	Book updateBook(Book bookValue) throws BookException;

	void deleteBookByIsbn(String isbn) throws BookException;

	Collection<Book> findAllBooks();

	@Event
	public class BookEvent {
		public BookEvent(String isbn) {
			super();
			this.isbn = isbn;
		}

		public String getIsbn() {
			return isbn;
		}

		private String isbn;
	}

	public interface BookEventType {
		@EventProducer public final String CREATED = "book.created";
		@EventProducer public final String UPDATED = "book.updated";
		@EventProducer public final String DELETED = "book.deleted";
	}

}
