package org.javacream.books.isbngenerator.impl;

import org.javacream.books.isbngenerator.api.IsbnGeneratorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("cIG")
@Qualifier(IsbnGeneratorService.Qualifier.SEQUENCE)

public class CounterIsbnGenerator implements IsbnGeneratorService {

    @Value("${isbngenerator.prefix}")
    private String prefix;
    @Value("${isbngenerator.country}")
    private String countryCode;

    private int counter;

    public String next() {
        return prefix + counter++ + countryCode;
    }

}
