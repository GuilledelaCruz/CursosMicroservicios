package es.guilledelacruz.currencyexchangeservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import es.guilledelacruz.currencyexchangeservice.beans.CurrencyExchange;
import es.guilledelacruz.currencyexchangeservice.repositories.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	public CurrencyExchange getCurrencyExchange(String from, String to) {
		Optional<CurrencyExchange> ce = repository.findByFromAndTo(from, to);
		
		if (ce == null)
			throw new RuntimeException("No echange found from " + from + " to " + to);
		
		CurrencyExchange item = ce.get();
		item.setEnvironment(environment.getProperty("local.server.port"));
		return item;
	}

}
