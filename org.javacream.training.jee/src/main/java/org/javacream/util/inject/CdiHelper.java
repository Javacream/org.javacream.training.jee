package org.javacream.util.inject;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class CdiHelper {

	@Resource(mappedName="ExampleDS")
	private DataSource ds;
	
	@ApplicationScoped @Produces public DataSource dataSource() {
		return ds;
	}
}
