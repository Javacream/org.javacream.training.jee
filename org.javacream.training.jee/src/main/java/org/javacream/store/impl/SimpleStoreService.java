package org.javacream.store.impl;

import javax.enterprise.context.ApplicationScoped;

import org.javacream.store.api.StoreService;

@ApplicationScoped
public class SimpleStoreService implements StoreService {
	private int stock;
	
	@Override
	public int getStock(String category, String item) {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
