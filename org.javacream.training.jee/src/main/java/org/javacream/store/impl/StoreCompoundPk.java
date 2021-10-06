package org.javacream.store.impl;

import java.io.Serializable;
import java.util.Objects;

public class StoreCompoundPk implements Serializable{

	public StoreCompoundPk() {
		super();
	}
	private static final long serialVersionUID = 1L;

	private String category;
	private String item;
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
	@Override
	public int hashCode() {
		return Objects.hash(category, item);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreCompoundPk other = (StoreCompoundPk) obj;
		return Objects.equals(category, other.category) && Objects.equals(item, other.item);
	}
	
}
