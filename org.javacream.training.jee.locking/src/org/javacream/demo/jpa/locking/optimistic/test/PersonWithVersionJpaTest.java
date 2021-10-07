package org.javacream.demo.jpa.locking.optimistic.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;

import org.javacream.demo.jpa.locking.optimistic.Person;
import org.junit.Test;

public class PersonWithVersionJpaTest {

	@Test
	public void testPersistence() {
		Person person = new Person("1", "Sawitzki", "Rainer", 79, 183, 'm');
		EntityManagerFactory ef = Persistence
				.createEntityManagerFactory("JPA");
		EntityManager entityManager1 = ef.createEntityManager();
		EntityManager entityManager2 = ef.createEntityManager();

		EntityTransaction transaction = entityManager1.getTransaction();
		transaction.begin();
		entityManager1.persist(person);
		transaction.commit();
		System.out.println(person);
		entityManager1.clear();

		Person loadedFromEntityManager1 = entityManager1.find(Person.class, 1l);
		Person loadedFromEntityManager2 = entityManager2.find(Person.class, 1l);

		System.out.println("From EntityManager1: " + loadedFromEntityManager1);
		System.out.println("From EntityManager2: " + loadedFromEntityManager2);

		loadedFromEntityManager1.setLastname("CHANGED FROM 1");
		loadedFromEntityManager2.setLastname("CHANGED FROM 2");

		loadedFromEntityManager2.setWeight(99.9);
		transaction = entityManager1.getTransaction();
		transaction.begin();
		entityManager1.merge(loadedFromEntityManager1);
		transaction.commit();
		loadedFromEntityManager1.setWeight(88.8);
		transaction.begin();
		entityManager1.merge(loadedFromEntityManager1);
		transaction.commit();
		
		
		transaction = entityManager2.getTransaction();
		transaction.begin();
		try {
			entityManager2.merge(loadedFromEntityManager2);
			entityManager2.flush();
			transaction.commit();
		} catch (OptimisticLockException e) {
			transaction.rollback();
			entityManager2.detach(loadedFromEntityManager2);
			Person changedPerson = entityManager2.find(Person.class, 1l);
			if (!changedPerson.getGivenName().equals(loadedFromEntityManager2.getGivenName())){
				System.out.println("Detected changed givenName: You entered: " + loadedFromEntityManager2.getGivenName() + ", was changed: " + changedPerson.getGivenName());
			}
			if (!(changedPerson.getGender() == loadedFromEntityManager2.getGender())){
				System.out.println("Detected changed gender: You entered: " + loadedFromEntityManager2.getGender() + ", was changed: " + changedPerson.getGender());
			}
			if (!(changedPerson.getHeight() == loadedFromEntityManager2.getHeight())){
				System.out.println("Detected changed height: You entered: " + loadedFromEntityManager2.getHeight() + ", was changed: " + changedPerson.getHeight());
			}
			if (!changedPerson.getLastname().equals(loadedFromEntityManager2.getLastname())){
				System.out.println("Detected changed lastname: You entered: " + loadedFromEntityManager2.getLastname() + ", was changed: " + changedPerson.getLastname());
			}
			if (!(changedPerson.getWeight() == loadedFromEntityManager2.getWeight())){
				System.out.println("Detected changed weight: You entered: " + loadedFromEntityManager2.getWeight() + ", was changed: " + changedPerson.getWeight());
				System.out.println("Merging weight...");
				transaction.begin();
				changedPerson.setWeight(loadedFromEntityManager2.getWeight());
				transaction.commit();

			}
			
		}
	}
}
