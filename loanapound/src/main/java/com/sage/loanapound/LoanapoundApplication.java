package com.sage.loanapound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@PropertySource({"classpath:application.yml"})
public class LoanapoundApplication {
	
//	private static final Log LOGGER = LogFactory.getLog(LoanapoundApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LoanapoundApplication.class, args);
	}
	
//		
//	
//	@Bean
//	public LocaleResolver localeResolver() {
//	    SessionLocaleResolver slr = new SessionLocaleResolver();
//	    slr.setDefaultLocale(Locale.ENGLISH);
//	    return slr;
//	}

	
		


	
}
