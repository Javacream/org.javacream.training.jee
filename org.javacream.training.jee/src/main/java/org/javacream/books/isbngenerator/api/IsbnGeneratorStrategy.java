package org.javacream.books.isbngenerator.api;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Usage: @IsbnGeneratorStragey(strategy="random") public class RandomIsbnGenerator ... @Inject @IsbnGeneratorStragey(strategy="random") 
 * @author rainersawitzki
 *
 */

@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD, TYPE_PARAMETER })
@Qualifier
public @interface IsbnGeneratorStrategy {

	String strategy();
}
