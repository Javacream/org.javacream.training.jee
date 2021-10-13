package org.javacream.books.warehouse.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.javacream.books.event.BookEvent;
import org.javacream.books.event.BookEvent.Created;
import org.javacream.books.event.BookEvent.Deleted;
import org.javacream.books.event.BookEvent.Updated;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

@Decorator
public abstract class NotifyingBooksService implements BooksService{

	@Inject
	@Created
	Event<BookEvent> createdEventSender;
	@Inject
	@Updated
	Event<BookEvent> updatedEventSender;
	@Inject
	@Deleted
	Event<BookEvent> deletedEventSender;

	public String newBook(String title) throws BookException {
		String isbn = booksService.newBook(title);
		createdEventSender.fire(new BookEvent(isbn));
		return isbn;
	}

	public Book updateBook(Book book) throws BookException {
		Book updatedBook =  booksService.updateBook(book);
		updatedEventSender.fire(new BookEvent(updatedBook.getIsbn()));
		return updatedBook;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		booksService.deleteBookByIsbn(isbn);
		deletedEventSender.fire(new BookEvent(isbn));
	}

	@Inject @Delegate @Any BooksService booksService;
}
