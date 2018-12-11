package org.javacream.books.warehouse.api;

import org.junit.Assert;
import org.junit.Test;

public class BookTests {
    @Test public void testBook(){
        Book b = new Book("ISBN1", "Title1", 100, 9.99, true);
        Assert.assertNotNull(b.info());

    }
}
