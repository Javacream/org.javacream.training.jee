package org.javacream.store.impl;

import javax.enterprise.event.Observes;

import org.javacream.books.event.BookEvent;
import org.javacream.store.api.StoreService;
import org.javacream.util.aop.TracingInterceptor.Tracing;

public class SimpleStoreService implements StoreService {
	private int stock;
	
	@Override
	@Tracing
	public int getStock(String category, String item) {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void handleBookDeletion(@Observes @BookEvent.Deleted BookEvent event) {
		System.out.println("SimpleStoreService: detected book deletion for isbn=" + event.getIsbn());
	}
}
