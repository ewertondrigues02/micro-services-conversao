package br.com.ewerton.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OpenApiConfiguration {

	@Lazy(value = false)
	@Bean
	public List<GroupedOpenApi> apis(SwaggerUiConfigParameters config, RouteDefinitionLocator locator ) {
		
		var definition = locator.getRouteDefinitions().collectList().block();
		definition.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
			String name = routeDefinition.getId();
			config.addGroup(name);
			GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
		});
		return new ArrayList<>();
	}
}
