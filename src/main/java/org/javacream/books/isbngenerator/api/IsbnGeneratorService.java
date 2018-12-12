package org.javacream.books.isbngenerator.api;

public interface IsbnGeneratorService {

    public abstract String next();

    public interface Qualifier{
        public String SEQUENCE = "sequence";
        public String RANDOM = "random";
    }

}