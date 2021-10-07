package org.javacream.publishing.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PUBLISHING_AUTHORS")
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long authorId;

	@Transient
	private List<String> givenNames;

	private String lastname;

	private String givenNameString;

	@ManyToMany(cascade=CascadeType.ALL)
	private Set<Book> books;

	Set<Book> getBooks() {
		if (books == null) {
			books = new HashSet<Book>();
		}
		return books;
	}

	void setBooks(Set<Book> books) {
		this.books = books;
	}

	public List<String> getGivenNames() {
		return givenNames;
	}

	public void setGivenNames(List<String> authorNames) {
		this.givenNames = authorNames;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Author other = (Author) obj;
		if (authorId == null) {
			if (other.authorId != null) {
				return false;
			}
		} else if (!authorId.equals(other.authorId)) {
			return false;
		}
		if (givenNameString == null) {
			if (other.givenNameString != null) {
				return false;
			}
		} else if (!givenNameString.equals(other.givenNameString)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result
				+ ((authorId == null) ? 0 : authorId.hashCode());
		result = PRIME * result
				+ ((givenNameString == null) ? 0 : givenNameString.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Author: id=" + authorId + ", name=" + givenNameString;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastName) {
		this.lastname = lastName;
	}

	@PostLoad
	public void initGivenNames() {
		StringTokenizer tokenizer = new StringTokenizer(givenNameString, ",");
		givenNames = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			givenNames.add(tokenizer.nextToken());
		}
	}

	@PreUpdate
	public void preUpdate() {
		initGivenNameString();
	}

	@PrePersist
	public void prePersist() {
		initGivenNameString();

	}

	private void initGivenNameString() {
		givenNameString = "";
		for (String givenName : givenNames) {
			givenNameString += givenName;
			givenNameString += ",";
		}
		givenNameString = givenNameString.substring(0,
				givenNameString.length() - 1);
	}

	public void addBook(Book book) {
		getBooks().add(book);
		book.getAuthorsSet().add(this);
	}

}
