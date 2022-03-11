package org.javacream.util.aspect;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Traced
public class TracingInterceptor {

	@AroundInvoke
	public Object trace(InvocationContext invocation) throws Exception{
		String methodName = invocation.getMethod().getName();
		Object[] params = invocation.getParameters();
		before(methodName, params);
		try {
			Object result = invocation.proceed();
			afterReturning(methodName, params, result);
			return result;
		}
		catch(Exception e) {
			afterThrowing(methodName, params, e);
			throw e;
		}
	}

	private void before(String methodName, Object[] parameters) {
		System.out.println("entering " + methodName);
	}
	private void afterReturning(String methodName, Object[] parameters, Object result) {
		System.out.println("returning from " + methodName + ", result=" + result);
	}
	private void afterThrowing(String methodName, Object[] parameters, Exception e) {
		System.out.println("throwing from " + methodName + ", exception=" + e);
	}
}
