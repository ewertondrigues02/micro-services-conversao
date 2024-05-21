package br.com.ewerton.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Foo bar")
@RestController
@RequestMapping(value = "/book-service")
public class FooBarController {

	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);
	
	@Operation(summary = "Foo bar")
	@GetMapping(value = "/foo-bar")
	@Retry(name = "default")
	public String fooBar() {
		logger.info("Request to foo-bar is received!");
		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		return response.getBody();
	}
}
