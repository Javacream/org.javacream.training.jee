package org.javacream.schedule;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/schedule")
@ApplicationScoped
public class SimpleScheduler {

	@Resource ManagedScheduledExecutorService executorService;
	private ScheduledFuture<?> future;
	@GET @Path("/start") public String schedule(){
		future = executorService.scheduleAtFixedRate(() -> System.out.println(System.currentTimeMillis()), 0, 2, TimeUnit.SECONDS);
		return "START OK";
	}
	@GET @Path("/stop") public String stop(){
		future.cancel(true);
		return "STOP OK";
	}
	
	}
