package org.javacream.books.isbngenerator.impl;

import org.javacream.books.isbngenerator.api.IsbnGeneratorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Qualifier(IsbnGeneratorService.Qualifier.RANDOM)
public class RandomIsbnGenerator implements IsbnGeneratorService {

    @Value("${isbngenerator.prefix}")
    private String prefix;
    @Value("${isbngenerator.country}")
    private String countryCode;

    private Random random;

    {
        random = new Random(this.hashCode() + System.currentTimeMillis());
    }

    public String next() {
        return prefix + random.nextInt() + countryCode;
    }
}
