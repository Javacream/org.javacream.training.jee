package org.javacream.publishing.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PUBLISHING_SCHOOLBOOK")
public class SchoolBook extends Book {

	SchoolBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SchoolBook(String description, Isbn isbn, int pages, double price,
			String title, BookStatistics bookInfo, boolean available, String subject, int year) {
		super(description, isbn, pages, price, title, bookInfo, available);
		this.subject = subject;
		this.year = year;
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Column(name="SCHOOL_YEAR")
	private int year;

	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
