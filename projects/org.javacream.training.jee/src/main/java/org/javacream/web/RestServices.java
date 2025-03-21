package org.javacream.web;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("api")
@OpenAPIDefinition(
        info = @Info(
                title = "My APIs",
                version = "1.0.0",
                description = "My REST APIs"

        )
)
public class RestServices extends Application{
	//Nichts verpflichtend zu implementieren
}
