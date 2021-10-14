package org.javacream.util.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.InvocationContext;

import org.javacream.util.aop.TracingInterceptor.Tracing;

@Interceptor
@Tracing
public class TracingInterceptor {

	@AroundInvoke
	public Object trace(InvocationContext invocationContext) throws Exception {
		System.out.println("entering method " + invocationContext.getMethod().getName());
		try {
			Object result = invocationContext.proceed();
			System.out.println("returning from method " + invocationContext.getMethod().getName());
			return result;
		} catch (Exception e) {
			System.out.println("throwing from method " + invocationContext.getMethod().getName());
			throw e;
		}
	}

	@InterceptorBinding
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface Tracing{
		
	}

}
