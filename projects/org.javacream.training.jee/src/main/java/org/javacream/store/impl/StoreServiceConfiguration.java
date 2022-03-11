package org.javacream.store.impl;

import javax.enterprise.inject.Produces;

import org.javacream.store.api.StoreService;
import org.javacream.store.api.StoreService.SimpleStrategy;
import org.javacream.util.Config;

public class StoreServiceConfiguration {

	@Produces @SimpleStrategy public StoreService storeService(SimpleStoreService simpleStoreService, @Config(property = "storeService.defaultStock") int stock) {
		simpleStoreService.setStock(stock);
		return simpleStoreService;
	}
}
