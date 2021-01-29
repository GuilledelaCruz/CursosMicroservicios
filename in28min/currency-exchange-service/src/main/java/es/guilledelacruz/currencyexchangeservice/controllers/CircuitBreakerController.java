package es.guilledelacruz.currencyexchangeservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	public String sampleApi() {
		logger.info("Sample api received");
		ResponseEntity<String> response =
		new RestTemplate().getForEntity("http://localhost:8080/dummy-test", String.class);
		
		return response.getBody();
	}
	
	@GetMapping("/sample-api-2")
	@CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
	@RateLimiter(name="default")
	@Bulkhead(name="default")
	public String sampleApi2() {
		logger.info("Sample api 2 received");
		ResponseEntity<String> response =
		new RestTemplate().getForEntity("http://localhost:8080/dummy-test", String.class);
		
		return response.getBody();
	}
	
	public String hardcodedResponse(Exception e) {
		return "FallbackResponse";
	}
}
