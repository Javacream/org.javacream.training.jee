package org.javacream.util.aspect;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

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
