package org.javacream.util.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

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
		String name = invocationContext.getMethod().getName();
		List<Object> params = Arrays.asList(invocationContext.getParameters());
		System.out.println("entering method " + name + ", params=" +params);
		try {
			Object result = invocationContext.proceed();
			System.out.println("returning from method " + name + ", result=" + result);
			return result;
		} catch (Exception e) {
			System.out.println("throwing from method " + name + ", exception=" + e);
			throw e;
		}
	}

	@InterceptorBinding
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface Tracing{
		
	}

}
