package es.guilledelacruz.currencyexchangeservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.guilledelacruz.currencyexchangeservice.beans.CurrencyExchange;
import es.guilledelacruz.currencyexchangeservice.services.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeService service;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
		return service.getCurrencyExchange(from, to);
	}

}
