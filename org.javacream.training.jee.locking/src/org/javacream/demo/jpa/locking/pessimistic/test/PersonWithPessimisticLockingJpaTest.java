package org.javacream.demo.jpa.locking.pessimistic.test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

import org.javacream.demo.jpa.locking.pessimistic.Person;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonWithPessimisticLockingJpaTest {

	private static EntityManager entityManager1;
	private static EntityManager entityManager2;
	private static ExecutorService executorService;

	@BeforeClass
	public static void createController() throws Exception {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPA");
		entityManager1 = factory.createEntityManager();
		entityManager2 = factory.createEntityManager();
		executorService = Executors.newFixedThreadPool(2);
	}

	@Test
	public void testLocking() {
		Person person = new Person("1", "Sawitzki", "Rainer", 79, 183, 'm');
		EntityTransaction transaction1 = entityManager1.getTransaction();
		transaction1.begin();
		entityManager1.persist(person);
		transaction1.commit();

		aquireLock1();
		aquireLock2();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void aquireLock2() {
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				EntityTransaction transaction = entityManager2.getTransaction();

				transaction.begin();
				Person p = entityManager2.find(Person.class, 1l);
				entityManager2.lock(p, LockModeType.PESSIMISTIC_WRITE);
				System.out.println("Lock 2 obtained at: " + new Date());
				transaction.commit();
			}

		});
	}

	private void aquireLock1() {
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				EntityTransaction transaction = entityManager1.getTransaction();

				transaction.begin();
				Person p = entityManager1.find(Person.class, 1l);
				entityManager1.lock(p, LockModeType.PESSIMISTIC_WRITE);
				System.out.println("Lock 1 obtained at: " + new Date());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				transaction.commit();
			}

		});
	}

}
