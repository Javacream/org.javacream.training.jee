package org.javacream.books.isbngenerator;

import org.javacream.books.isbngenerator.api.IsbnGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsbnGeneratorTest {

    @Autowired @Qualifier(IsbnGeneratorService.Qualifier.RANDOM)
    private IsbnGeneratorService isbnGenerator;

    @Test
    public void testIsbnGeneration() {
        System.out.println(isbnGenerator.next());
    }
}
