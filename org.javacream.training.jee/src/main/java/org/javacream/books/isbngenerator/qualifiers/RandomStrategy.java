package org.javacream.books.isbngenerator.qualifiers;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Retention(RUNTIME)
@Target({TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Qualifier
public @interface RandomStrategy {

}
