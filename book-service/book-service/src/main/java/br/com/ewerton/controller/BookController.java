package br.com.ewerton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ewerton.model.Book;
import br.com.ewerton.proxy.CambioProxy;
import br.com.ewerton.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "book endpoint")
@RestController
@RequestMapping(value = "/book-service")
public class BookController {

	@Autowired
	private BookRepository repository;

	@Autowired
	private Environment environment;

	@Autowired
	private CambioProxy proxy;

	
	@Operation(summary = "Find a specific book by your ID")
	@GetMapping("/{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

		var book = repository.getReferenceById(id);
		if (book == null)
			throw new RuntimeException("Book not found");

		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		var port = environment.getProperty("local.server.port");

		book.setEnvironment("Book Port: " + port + " Cambio port: " + cambio.getEnvironment());
		book.setPrice(cambio.getConvertedValue());

		return book;
	}

}
