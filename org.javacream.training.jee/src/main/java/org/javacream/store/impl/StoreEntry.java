package org.javacream.store.impl;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "STORE") //weglassen: STORE_ENTRY
@IdClass(StoreCompoundPk.class)
public class StoreEntry {

	@Transient
	private String info;
	//@Column(name = "COL_STOCK") // -> Tabellenspalte heißt COL_STOCK
	private int stock; //Tabellenspalte heißt STOCK

	@Id
	private String category;
	@Id
	private String item;
	
	@Override
	public int hashCode() {
		return Objects.hash(category, item, stock);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreEntry other = (StoreEntry) obj;
		return Objects.equals(category, other.category) && Objects.equals(item, other.item) && stock == other.stock;
	}
	public StoreEntry() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StoreEntry(int stock, String category, String item) {
		super();
		this.stock = stock;
		this.category = category;
		this.item = item;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
}
