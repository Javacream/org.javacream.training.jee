package org.javacream.util;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Stereotype;

@Retention(RUNTIME)
@Target({TYPE, ElementType.METHOD})
@Alternative
@Stereotype
public @interface Prod {

}
