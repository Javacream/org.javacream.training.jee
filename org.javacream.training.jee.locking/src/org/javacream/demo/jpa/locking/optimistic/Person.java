package org.javacream.demo.jpa.locking.optimistic;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;


@Entity(name="VersionedPerson")
@Table(name = "PEOPLE_WITH_VERSION")
@Access(AccessType.FIELD)
public class Person {

	@Column(name = "SOCIAL_NUMBER")
	private String socialId;
	@Column(nullable = false, length=50)
	private String lastname;
	private String givenName;
	private double weight;
	private int height;

	@Version
	private Long versionNumber;
	
	@Override
	public String toString() {
		return "Person [socialId=" + socialId + ", lastname=" + lastname
				+ ", givenName=" + givenName + ", weight=" + weight
				+ ", height=" + height + ", versionNumber=" + versionNumber
				+ ", gender=" + gender + ", personId=" + personId
				+ ", toString()=" + super.toString() + "]";
	}

	@Transient
	private char gender;

	public String getLastname() {
		return lastname;
	}

	public Person(String socialId, String lastname, String givenName,
			double weight, int height, char gender) {
		super();
		this.socialId = socialId;
		this.lastname = lastname;
		this.givenName = givenName;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getSocialId() {
		return socialId;
	}

	public String getGivenName() {
		return givenName;
	}

	public char getGender() {
		return gender;
	}

	public String sayHello() {
		return "Hello from " + lastname;

	}

	// JPA-konform
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long personId;

	/*
	 * Nicht unbedingt notwendig!!!!
	 */
	protected Long getPersonId() {
		return personId;
	}

	@SuppressWarnings("unused")
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((socialId == null) ? 0 : socialId.hashCode());
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
		Person other = (Person) obj;
		if (socialId == null) {
			if (other.socialId != null)
				return false;
		} else if (!socialId.equals(other.socialId))
			return false;
		return true;
	}

}
