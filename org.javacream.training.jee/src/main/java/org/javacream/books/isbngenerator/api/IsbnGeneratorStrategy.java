package org.javacream.books.isbngenerator.api;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * 
 * Usage: 
 * @IsbnGeneratorStrategy(strategy="sequence") public class...,
 * @Inject IsbnGeneratorStrategy(strategy="sequence") private IsbnGenerator ..., 
 * @author rainersawitzki
 *
 */
@Retention(RUNTIME)
@Target({ TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Qualifier
public @interface IsbnGeneratorStrategy {
	String strategy();
}
