package org.javacream.books.warehouse.impl;

import org.javacream.books.warehouse.api.Book;

import java.util.Arrays;
import java.util.HashSet;

public class BooksFavorites {
    private HashSet<Book> favorites = new HashSet<Book>();

    public void add(Book... favorite) {
        favorites.addAll(Arrays.asList(favorite));
    }

    public void remove(Book favorite) {
        favorites.remove(favorite);
    }

    public void show() {
        for (Book b : favorites) {
            System.out.println(b.info());
        }
    }
}
