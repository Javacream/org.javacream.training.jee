package org.javacream.util.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.InvocationContext;

import org.javacream.util.aop.ProfilingInterceptor.Profiling;

@Interceptor
@Profiling
public class ProfilingInterceptor {

	@AroundInvoke
	public Object trace(InvocationContext invocationContext) throws Exception {
		long start = System.currentTimeMillis();
		try {
			Object result = invocationContext.proceed();
			return result;
		} finally {
			System.out.println("Calling " + invocationContext.getMethod().getName() + " took "
					+ (System.currentTimeMillis() - start));
		}
	}

	@InterceptorBinding
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE, ElementType.METHOD })
	public @interface Profiling {

	}

}
