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

	
		
//	@Bean
//	CommandLineRunner lookup(Company1Soap scoreClient) {
//		return args -> {
//			String name = "Pepe";
//
//			GetScoreResponse response = scoreClient.getScore(name);
//			System.err.println("El score para " + name + " es: " + response.getScore());
//		};
//	}
//	
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}
//
//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			Integer quote = restTemplate.getForObject(
//					"http://localhost:83/score?idcard=12", Integer.class);
//			LOGGER.info("LA OPERACIÓN REST DEVUELVE: " + quote.toString());
//		};
//	}

	
}
