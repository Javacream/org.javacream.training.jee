package org.javacream.books.warehouse.api;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String isbn;

    private String title;

    private double price;
    private Integer pages;

    private boolean available;

    public Book() {
    }

    public Book(String isbn, String title, Integer pages, double price, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.pages = pages;
        this.available = available;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return isbn != null ? isbn.equals(book.isbn) : book.isbn == null;
    }

    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }

    public String info() {
        return (new StringBuffer("BookValue: isbn=").append(isbn).append(
                ", title=").append(title).append(", price=").append(price)
                .append(
                        ", available=").append(available)).toString();
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


}
