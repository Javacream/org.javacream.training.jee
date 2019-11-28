package org.javacream.store.api;

import org.javacream.util.stereotype.EventObserver;

public interface StoreService {
	int getStock(String category, String item);
	
	public interface StoreEventType {
		@EventObserver public final String CREATED = "book.created";
		@EventObserver public final String DELETED = "book.deleted";
	}

}
