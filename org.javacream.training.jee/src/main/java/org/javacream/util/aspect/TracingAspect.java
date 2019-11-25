package org.javacream.util.aspect;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Trace
public class TracingAspect {

	@AroundInvoke
	public Object trace(InvocationContext invocationContext) throws Throwable{
		String methodName = invocationContext.getMethod().getName();
		System.out.println("entering " + methodName);
		try {
			Object result = invocationContext.proceed();
			System.out.println("returning from " + methodName);
			return result;
		}
		catch(Throwable t) {
			System.out.println("throwing from " + methodName);
			throw t;
		}
	}
}
