package org.javacream.books.warehouse.impl;

import org.javacream.books.warehouse.api.BooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto training@rainer-sawitzki.de
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksServiceTest {
    @Autowired private BooksService booksService;
    @Test
    public void testBusinessObjects() {
        TestActor.doTest(booksService);


    }


}
