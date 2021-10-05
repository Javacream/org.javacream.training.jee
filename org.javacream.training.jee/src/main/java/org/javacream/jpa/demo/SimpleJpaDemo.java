package org.javacream.jpa.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/jpa/demo")
public class SimpleJpaDemo {

	@PersistenceContext private EntityManager entityManager;
	@GET
	@Transactional
	public String doDemo() {
//		//Im Appserver nicht zulässig
//		EntityTransaction t = entityManager.getTransaction();
//		t.begin();
//		//...
//		t.commit();	
		
		entityManager.createNativeQuery("create table messages (col_message varchar(256))").executeUpdate();
		entityManager.createNativeQuery("insert into messages values('hello')").executeUpdate();
		List<String> result = entityManager.createNativeQuery("select * from messages").getResultList();
		entityManager.createNativeQuery("drop table messages").executeUpdate();
		return "OK, result=" + result;
	}
}
