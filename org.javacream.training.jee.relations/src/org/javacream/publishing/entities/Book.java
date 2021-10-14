package org.javacream.publishing.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;



/**
 * The persistent class for the BOOK database table.
 * 
 */
@Entity
@Table(name="PUBLISHING_BOOKS", uniqueConstraints={@UniqueConstraint(columnNames={"isbn1", "isbn2", "isbn3", "isbn4"})})
@Inheritance(strategy=InheritanceType.JOINED)
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	//@ManyToOne(fetch=FetchType.LAZY)
	@ManyToOne //fetch=FetchType.EAGER
	private Publisher publisher;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "BOOK_STATISTICS_ID", unique = true, nullable = false, updatable = false)
	protected BookStatistics bookInfo;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "BOOKS_AUTHORS", joinColumns = { @JoinColumn(name = "ISBN") }, inverseJoinColumns = { @JoinColumn(name = "AUTHOR_ID") })
	private Set<Author> authors;


	@ElementCollection
	private List<String> keywords;

	
	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", description=" + description + ", isbn="
				+ isbn + ", pages=" + pages + ", price=" + price + ", title="
				+ title + ", bookInfo=" + bookInfo + ", available=" + available
				+ ", toString()=" + super.toString() + "]";
	}

	@Basic(fetch=FetchType.LAZY)
	private String description;

	@Embedded
	private Isbn isbn;

	@Column(columnDefinition="INTEGER")
	private int pages;

	private double price;

	@Column(unique=true)
	private String title;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	
	@Transient
	private boolean available;
    public Book(String description, Isbn isbn, int pages, double price,
			String title, BookStatistics bookInfo, boolean available) {
		super();
		this.description = description;
		this.isbn = isbn;
		this.pages = pages;
		this.price = price;
		this.title = title;
		this.bookInfo = bookInfo;
		this.available = available;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public BookStatistics getBookInfo() {
		return bookInfo;
	}

	Book() {
    }

	public long getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Isbn getIsbn() {
		return this.isbn;
	}

	public void setIsbn(Isbn isbn) {
		this.isbn = isbn;
	}

	public int getPages() {
		return this.pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	Set<Author> getAuthorsSet() {
		if (authors == null){
			authors = new HashSet<Author>();
		}
		return authors;
	}
	public Set<Author> getAuthors() {
		if (authors == null){
			authors = new HashSet<Author>();
		}
		return Collections.unmodifiableSet(authors);
	}
}