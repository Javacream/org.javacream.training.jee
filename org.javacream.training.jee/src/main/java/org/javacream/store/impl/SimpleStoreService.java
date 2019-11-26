package org.javacream.store.impl;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

import org.javacream.books.warehouse.api.BookChanged;
import org.javacream.store.api.StoreService;
import org.javacream.util.inject.Business;

@RequestScoped
@Business
public class SimpleStoreService implements StoreService {
	private int stock = 42;
	
	@Override
	public int getStock(String category, String item) {
		System.out.println("############# " + this.hashCode());
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void handleBookCreated(@Observes @BookChanged.Type(BookChanged.BookChangeType.CREATED) BookChanged bookChanged) {
		System.out.println("BOOK CREATED: " + bookChanged.getIsbn());
	}
	public void handleBookDeleted(@Observes @BookChanged.Type(BookChanged.BookChangeType.DELETED) BookChanged bookChanged) {
		System.out.println("BOOK DELETED: " + bookChanged.getIsbn());
	}

}
