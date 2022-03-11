package org.javacream.books.isbngenerator.api;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

public interface IsbnGenerator {

	public abstract String next();

	@Retention(RUNTIME)
	@Target({ TYPE, METHOD, ElementType.FIELD, ElementType.PARAMETER })
	@Qualifier
	public @interface RandomStrategy {

	}

	@Retention(RUNTIME)
	@Target({ TYPE, METHOD, ElementType.FIELD, ElementType.PARAMETER })
	@Qualifier
	public @interface SequenceStrategy {

	}

}