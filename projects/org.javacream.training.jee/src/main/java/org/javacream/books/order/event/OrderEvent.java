package org.javacream.books.order.event;

import java.io.Serializable;
import java.util.Objects;

public class OrderEvent implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long orderId;
	private String isbn;
	private int number;
	private boolean ok;
	@Override
	public String toString() {
		return "OrderEvent [orderId=" + orderId + ", isbn=" + isbn + ", number=" + number + ", ok=" + ok + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(isbn, number, ok, orderId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEvent other = (OrderEvent) obj;
		return Objects.equals(isbn, other.isbn) && number == other.number && ok == other.ok
				&& Objects.equals(orderId, other.orderId);
	}
	public Long getOrderId() {
		return orderId;
	}
	public String getIsbn() {
		return isbn;
	}
	public int getNumber() {
		return number;
	}
	public boolean isOk() {
		return ok;
	}
	public OrderEvent(Long orderId, String isbn, int number, boolean ok) {
		super();
		this.orderId = orderId;
		this.isbn = isbn;
		this.number = number;
		this.ok = ok;
	}

}
