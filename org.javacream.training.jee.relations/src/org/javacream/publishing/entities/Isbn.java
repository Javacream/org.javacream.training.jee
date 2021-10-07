package org.javacream.publishing.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Isbn implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private int isbn1;
	@Column(nullable=false)
	private int isbn2;
	@Column(nullable=false)
	private int isbn3;
	@Column(nullable=false)
	private int isbn4;

	@SuppressWarnings("unused")
	public Isbn() {
	}
	public Isbn(int isbn1, int isbn2, int isbn3, int isbn4) {
		super();
		this.isbn1 = isbn1;
		this.isbn2 = isbn2;
		this.isbn3 = isbn3;
		this.isbn4 = isbn4;
	}
	@Override
	public String toString() {
		return "Isbn [isbn1=" + isbn1 + ", isbn2=" + isbn2 + ", isbn3=" + isbn3
				+ ", isbn4=" + isbn4 + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + isbn1;
		result = prime * result + isbn2;
		result = prime * result + isbn3;
		result = prime * result + isbn4;
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
		Isbn other = (Isbn) obj;
		if (isbn1 != other.isbn1)
			return false;
		if (isbn2 != other.isbn2)
			return false;
		if (isbn3 != other.isbn3)
			return false;
		if (isbn4 != other.isbn4)
			return false;
		return true;
	}
	public int getIsbn1() {
		return isbn1;
	}
	public int getIsbn2() {
		return isbn2;
	}
	public int getIsbn3() {
		return isbn3;
	}
	public int getIsbn4() {
		return isbn4;
	}
	
}
