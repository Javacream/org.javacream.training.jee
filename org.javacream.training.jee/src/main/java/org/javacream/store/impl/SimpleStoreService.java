package org.javacream.store.impl;

import javax.enterprise.context.RequestScoped;

import org.javacream.store.api.StoreService;

@RequestScoped
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

}
