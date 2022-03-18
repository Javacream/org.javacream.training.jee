package org.javacream.books.warehouse.event;

import java.util.Objects;

public class BookEvent {

	private String isbn;

	public BookEvent(String isbn) {
		super();
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "BookEvent [isbn=" + isbn + "]";
	}

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

	public String getIsbn() {
		return isbn;
	}
	
	
}
