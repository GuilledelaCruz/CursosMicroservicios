package es.guilledelacruz.limitsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.guilledelacruz.limitsservice.beans.Limits;
import es.guilledelacruz.limitsservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration config;

	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(
				config.getMinimum(), 
				config.getMaximum()
		);
	}
}
