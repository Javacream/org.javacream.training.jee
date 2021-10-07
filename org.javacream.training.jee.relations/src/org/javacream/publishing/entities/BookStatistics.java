package org.javacream.publishing.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PUBLISHING_BOOKS_STATISTICS")
public class BookStatistics implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@OneToOne(mappedBy="bookInfo")
	private Book book;
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Long getId(){
		return this.id;
	}
	private static final long serialVersionUID = 1L;

	private int sold;
	
	@Temporal(TemporalType.TIME)
	private Date lastSold;

	BookStatistics() {
	}
	
	public BookStatistics(int sold, Date lastSold) {
		super();
		this.sold = sold;
		this.lastSold = lastSold;
	}
	@Override
	public String toString() {
		return "BookInfo [sold=" + sold + ", lastSold=" + lastSold + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lastSold == null) ? 0 : lastSold.hashCode());
		result = prime * result + sold;
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
		BookStatistics other = (BookStatistics) obj;
		if (lastSold == null) {
			if (other.lastSold != null)
				return false;
		} else if (!lastSold.equals(other.lastSold))
			return false;
		if (sold != other.sold)
			return false;
		return true;
	}
}
