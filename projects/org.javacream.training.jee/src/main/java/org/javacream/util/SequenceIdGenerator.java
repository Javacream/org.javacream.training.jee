package org.javacream.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class SequenceIdGenerator {

	private long counter = 0;
	public Long next() {
		return counter++;
	}


	@Produces @Named("forIsbnGenerator") public SequenceIdGenerator forIsbnGenerator(){
		return new SequenceIdGenerator();
	}
	@Produces @Named("forOrderService") public SequenceIdGenerator forOrderService(){
		return new SequenceIdGenerator();
	}

	
}

