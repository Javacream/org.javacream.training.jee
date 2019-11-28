package org.javacream.store.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@IdClass(StoreId.class)
@Entity
public class StoreEntry {

	@Id
	private String category;
	@Id
	private String item;
	private int stock;

	public StoreEntry(String category, String item, int stock) {
		super();
		this.category = category;
		this.item = item;
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public String getItem() {
		return item;
	}
	public int getStock() {
		return stock;
	}
	//JPA-Spec
	public StoreEntry() {
		
	}
}
