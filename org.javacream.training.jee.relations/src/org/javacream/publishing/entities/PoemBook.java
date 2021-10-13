package org.javacream.publishing.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PUBLISHING_POEMS")
public class PoemBook extends Book {

	private String era;

	@Override
	public String toString() {
		return "PoemBook [era=" + era + ", id=" + poemId + ", year=" + year
				+ ", subject=" + subject + ", toString()=" + super.toString()
				+ "]";
	}

	public String getEra() {
		return era;
	}

	public void setEra(String era) {
		this.era = era;
	}

	//@Id
	private Long poemId;

	PoemBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PoemBook(String description, Isbn isbn, int pages, double price,
			String title, BookStatistics bookInfo, boolean available, String era, Long id) {
		super(description, isbn, pages, price, title, bookInfo, available);
		this.poemId = id;
		this.era = era;
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
