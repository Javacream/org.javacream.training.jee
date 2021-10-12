package org.javacream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;

@ApplicationScoped
public class ApplicationProducer {
	
	@Produces @ApplicationScoped StoreService storeService() {
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		simpleStoreService.setStock(42);
		return simpleStoreService;
	}

}
