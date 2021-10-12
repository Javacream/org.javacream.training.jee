package org.javacream.books.isbngenerator.api;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

public interface IsbnGenerator {

	public abstract String next();

	@Retention(RUNTIME)
	@Target({ TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
	@Qualifier
	public @interface RandomStrategy {

	}
	@Retention(RUNTIME)
	@Target({ TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
	@Qualifier
	public @interface SequenceStrategy {

	}

}