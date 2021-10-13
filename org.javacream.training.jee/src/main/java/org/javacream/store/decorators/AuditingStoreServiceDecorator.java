package org.javacream.store.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.javacream.store.api.StoreService;

@Decorator
public abstract class AuditingStoreServiceDecorator implements StoreService{

	@Inject @Delegate @Any private StoreService storeService;

	public int getStock(String category, String item) {
		System.out.println("DECORATING");
		return storeService.getStock(category, item);
	}
}
