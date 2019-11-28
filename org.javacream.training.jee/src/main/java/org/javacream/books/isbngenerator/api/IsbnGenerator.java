package org.javacream.books.isbngenerator.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

public interface IsbnGenerator {

	public abstract String next();

	@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Qualifier
	public @interface IsbnGeneratorQualifier {

		IsbnGeneratorStrategy value();
	}

	public enum IsbnGeneratorStrategy {

		SEQUENCE, RANDOM;
	}
}