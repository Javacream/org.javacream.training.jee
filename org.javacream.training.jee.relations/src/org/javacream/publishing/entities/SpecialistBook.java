package org.javacream.publishing.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PUBLISHING_SPECIALISTBOOK")
public class SpecialistBook extends Book{

	SpecialistBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpecialistBook(String description, Isbn isbn, int pages,
			double price, String title, BookStatistics bookInfo, boolean available, String category) {
		super(description, isbn, pages, price, title, bookInfo, available);
		this.category = category;
	}
	private static final long serialVersionUID = 1L;
	private String category;
	public String getCategory() {
		return category;
	}
	public void setCategory(String area) {
		this.category = area;
	}
}
