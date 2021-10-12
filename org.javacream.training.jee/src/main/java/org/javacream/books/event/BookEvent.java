package org.javacream.books.event;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Objects;

import javax.inject.Qualifier;

public class BookEvent implements Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEvent other = (BookEvent) obj;
		return Objects.equals(isbn, other.isbn);
	}

	private String isbn;

	public BookEvent(String isbn) {
		super();
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	@Retention(RUNTIME)
	@Target({ TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR })
	@Qualifier
	public static @interface Created{
		
	}
	@Retention(RUNTIME)
	@Target({ TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR })
	@Qualifier
	public static @interface Updated{
		
	}
	@Retention(RUNTIME)
	@Target({ TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR })
	@Qualifier
	public static @interface Deleted{
		
	}
}
