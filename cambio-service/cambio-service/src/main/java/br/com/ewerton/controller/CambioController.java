package br.com.ewerton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ewerton.model.Cambio;
import br.com.ewerton.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cambio Service API")
@RestController
@RequestMapping(value = "/cambio-service")
public class CambioController {

	@Autowired
	private Environment environment;

	@Autowired
	private CambioRepository repository;

	@Operation(description = "Get Cambio from currency")
	@GetMapping("/{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") Double amount, @PathVariable("from") String from, @PathVariable("to") String to) {

		var port = environment.getProperty("local.server.port");
		Cambio cambio = repository.findByFromAndTo(from, to);
		if (cambio == null) throw new RuntimeException("Currency Unsopported");

		Double conversionFactor = cambio.getConversionFactor();
		Double convertedValue = conversionFactor * amount;
		cambio.setConvertedValue(convertedValue);
		cambio.setEnvironment(port);

		return cambio;
	}

}
