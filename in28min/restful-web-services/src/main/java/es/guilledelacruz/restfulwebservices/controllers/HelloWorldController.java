package es.guilledelacruz.restfulwebservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.guilledelacruz.restfulwebservices.beans.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hello-world")
	public String getHelloWorld(/*@RequestHeader(name="Accept-Language", required = false) Locale locale*/) {
		return messageSource.getMessage("message.helloworld", null, LocaleContextHolder.getLocale());
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean();
	}

	@GetMapping("/hello-world-bean/{value}")
	public HelloWorldBean getHelloWorldBeanVariable(@PathVariable String value) {
		return new HelloWorldBean(value);
	}
}
