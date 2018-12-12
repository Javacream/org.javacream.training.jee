package org.javacream.books.warehouse.impl;

import org.apache.commons.lang3.SerializationUtils;
import org.javacream.Helper;
import org.javacream.books.isbngenerator.api.IsbnGeneratorService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 */

@Component
public class MapBooksService implements BooksService {
    @Autowired private Helper helper;

    {
        System.out.println("Initializing " + this);
        this.books = new HashMap<String, Book>();
    }


    @PostConstruct public void initBooksService(){
        helper.dump(this);
    }

    @Autowired @Qualifier(IsbnGeneratorService.Qualifier.SEQUENCE)
    private IsbnGeneratorService isbnGenerator;
    private Map<String, Book> books;
    @Autowired private StoreService storeService;

    @Override
    public Collection<Book> findBooksByTitle(String titleFilter) {
        //(Book book) -> {return book.getTitle().contains(titleFilter);}
        //book -> book.getTitle().contains(titleFilter)
        return books.values().stream().filter(book -> book.getTitle().contains(titleFilter)).map(book -> setAvailability(book)).collect(Collectors.toList());
    }

    private Book setAvailability(Book book){
        book.setAvailable((storeService.getStock("books", book.getIsbn()) > 0));
        return book;
    }
    public String newBook(String title) throws BookException {
            String isbn = isbnGenerator.next();
            Book book = new Book();
            book.setIsbn(isbn);
            book.setTitle(title);
            books.put(isbn, book);
            return isbn;
    }

    public IsbnGeneratorService getIsbnGenerator() {
        return isbnGenerator;
    }

    public Book findBookByIsbn(String isbn) throws BookException {
        Book result = (Book) books.get(isbn);
        if (result == null) {
            throw new BookException(BookException.BookExceptionType.NOT_FOUND,
                    isbn);
        }
        result.setAvailable(storeService.getStock("books", isbn) > 0);

        return SerializationUtils.clone(result);
    }

    public Book updateBook(Book bookValue) throws BookException {
        books.put(bookValue.getIsbn(), SerializationUtils.clone(bookValue));
        return bookValue;
    }

    public void deleteBookByIsbn(String isbn) throws BookException {
        Object result = books.remove(isbn);
        if (result == null) {
            throw new BookException(
                    BookException.BookExceptionType.NOT_DELETED, isbn);
        }
    }


    public Collection<Book> findAllBooks() {
        List<Book> result = books.values().stream().map(book -> setAvailability(book)).collect(Collectors.toList());
        return SerializationUtils.clone(new ArrayList<Book>(result));
    }

}
