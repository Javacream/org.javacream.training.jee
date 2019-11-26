package org.javacream.util.cdi.test;

import java.lang.annotation.Annotation;

import javax.enterprise.event.Event;
import javax.enterprise.util.TypeLiteral;

public class MockEventEmitter<T> implements Event<T>{

	@Override
	public void fire(T event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Event<T> select(Annotation... qualifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends T> Event<U> select(Class<U> subtype, Annotation... qualifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends T> Event<U> select(TypeLiteral<U> subtype, Annotation... qualifiers) {
		// TODO Auto-generated method stub
		return null;
	}


}
