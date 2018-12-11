package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

import java.util.Collection;

import static org.junit.Assert.*;

public class TestActor {

    public static void doTest(BooksService booksService) {
        try {
            Collection<Book> books = booksService.findAllBooks();
            final int startSize = books.size();
            String bookTitle = "Spring";
            String isbn = booksService.newBook(bookTitle);
            books = booksService.findAllBooks();
            final int endSize = books.size();
            assertTrue(endSize == startSize + 1);
            assertNotNull(isbn);
            assertNotNull(booksService.findBookByIsbn(isbn));
            assertTrue(1 == booksService.findBooksByTitle("Spri").size());
            assertTrue(0 == booksService.findBooksByTitle("JEE").size());

            try {
                booksService.findBookByIsbn("ISBN-3");
                fail("BookException must be thrown!");
            } catch (BookException e) {
                // OK
            }
            booksService.deleteBookByIsbn(isbn);
            assertTrue(startSize == booksService.findAllBooks().size());
            try {
                booksService.deleteBookByIsbn(isbn);
                fail("BookException must be thrown!");
            } catch (BookException e) {
                // OK
            }

        } catch (BookException bookException) {
            bookException.printStackTrace();
            fail(bookException.getMessage());
        }


    }

}
