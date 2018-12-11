package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.impl.BooksFavorites;
import org.junit.Test;

public class BooksFavoriteTest {
    @Test public void testBooksFavorites(){
        Book b1 = new Book("ISBN1", "Title1", 200, 19.99,true);
        Book duplicate = new Book("ISBN1", "Title1", 200, 19.99,true);
        Book b2 = new Book("ISBN2", "Title2", 100, 9.99,true);
        Book b3 = new Book("ISBN3", "Title3", 10, 1.99,true);
        Book b4 = new Book("ISBN4", "Title4", 300, 49.99,true);
        BooksFavorites booksFavorites = new BooksFavorites();
        booksFavorites.add(b1, duplicate, b4, b3);
        booksFavorites.show();
        booksFavorites.remove(b1);
        booksFavorites.show();
    }
}
