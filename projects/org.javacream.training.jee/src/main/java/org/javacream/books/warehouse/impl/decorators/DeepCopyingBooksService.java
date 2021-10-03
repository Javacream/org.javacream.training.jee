package org.javacream.books.warehouse.impl.decorators;

import java.util.ArrayList;
import java.util.Collection;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.apache.commons.lang3.SerializationUtils;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

@Decorator
public abstract class DeepCopyingBooksService implements BooksService{

	@Inject @Any @Delegate private BooksService booksService;

	public Book findBookByIsbn(String isbn) throws BookException {
		return SerializationUtils.clone(booksService.findBookByIsbn(isbn));
	}

	public Book updateBook(Book bookValue) throws BookException {
		return SerializationUtils.clone(booksService.updateBook(SerializationUtils.clone(bookValue)));
	}

	public Collection<Book> findAllBooks() {
		return SerializationUtils.clone(new ArrayList<>(booksService.findAllBooks()));
	}
}
