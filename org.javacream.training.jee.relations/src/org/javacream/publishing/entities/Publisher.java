package org.javacream.publishing.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.javacream.util.Address;
import org.javacream.util.Addressable;

/**
 * The persistent class for the PUBLISHERS database table.
 * 
 */
@Entity
@Table(name = "PUBLISHING_PUBLISHERS")
public class Publisher implements Serializable, Addressable {
	public Publisher(String publisherName, Set<Book> books, Address address) {
		super();
		this.publisherName = publisherName;
		this.books = books;
		this.address = address;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long publisherId;

	private String publisherName;

	// bi-directional many-to-one association to Book
	@OneToMany(mappedBy="publisher", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//@OneToMany(mappedBy = "publisher") //fetch=FetchType.LAZY
	private Set<Book> books;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((publisherName == null) ? 0 : publisherName.hashCode());
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
		Publisher other = (Publisher) obj;
		if (publisherName == null) {
			if (other.publisherName != null)
				return false;
		} else if (!publisherName.equals(other.publisherName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", publisherName="
				+ publisherName + ", address=" + address + ", toString()="
				+ super.toString() + "]";
	}

	//@SuppressWarnings("unused")
	Publisher() {
	}

	public long getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(long publisherid) {
		this.publisherId = publisherid;
	}

	public String getPublisherName() {
		return this.publisherName;
	}

	public void setPublisherName(String publishername) {
		this.publisherName = publishername;
	}

	public Set<Book> getBooks() {
		return Collections.unmodifiableSet(this.books);
	}

	public void addBooks(Book... books) {
		for (Book book : books) {
			this.books.add(book);
			book.setPublisher(this);
		}
	}

	@Embedded private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}