package es.guilledelacruz.currencyconversionservice.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.guilledelacruz.currencyconversionservice.beans.CurrencyConversion;
import es.guilledelacruz.currencyconversionservice.services.CurrencyConversionService;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyConversionService service;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		return service.getCurrencyConversion(from, to, quantity);
	}

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}/proxy")
	public CurrencyConversion getCurrencyConversionProxy(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		return service.getCurrencyConversionProxy(from, to, quantity);
	}
}
