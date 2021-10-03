package org.javacream.util.aspect;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@Monitored
public class MonitoringInterceptor {

	@AroundInvoke
	public Object monitor(InvocationContext invocation) throws Exception{
		String methodName = invocation.getMethod().getName();
		long start = System.currentTimeMillis();
		try {
			return invocation.proceed();
		}
		finally {
			System.out.println("Calling method " + methodName + " took " + (System.currentTimeMillis() - start) + "msec");
		}
	}

}
