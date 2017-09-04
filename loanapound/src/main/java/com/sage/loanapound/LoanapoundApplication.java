package com.sage.loanapound;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@PropertySource({"classpath:application.yml"})
public class LoanapoundApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(LoanapoundApplication.class, args);
	}
		
	@Bean
	public SessionLocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.ENGLISH);
	    return slr;
	}

}
