package es.guilledelacruz.currencyconversionservice.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.guilledelacruz.currencyconversionservice.beans.CurrencyConversion;
import es.guilledelacruz.currencyconversionservice.proxies.CurrencyExchangeProxy;

@Service
public class CurrencyConversionService {
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	public CurrencyConversion getCurrencyConversion(String from, String to, BigDecimal quantity) {
		
		Map<String, String> uriMap = new HashMap<>();
		uriMap.put("from", from);
		uriMap.put("to", to);
		ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriMap);
		
		if (response.getStatusCode() != HttpStatus.OK)
			throw new RuntimeException("No conversion found from " + from + " to " + to);
		
		CurrencyConversion cc = response.getBody();
		cc.setQuantity(quantity);
		cc.setTotalCalculatedAmount(cc.getQuantity().multiply(cc.getConversionMultiple()));
		
		return cc;
	}
	
	public CurrencyConversion getCurrencyConversionProxy(String from, String to, BigDecimal quantity) {
		CurrencyConversion cc = proxy.getCurrencyExchange(from, to);
		
		if (cc == null)
			throw new RuntimeException("No conversion found from " + from + " to " + to);
		
		cc.setQuantity(quantity);
		cc.setTotalCalculatedAmount(cc.getQuantity().multiply(cc.getConversionMultiple()));
		
		return cc;
	}
	
}
