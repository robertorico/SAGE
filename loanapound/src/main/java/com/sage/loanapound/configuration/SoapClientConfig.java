package com.sage.loanapound.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.sage.loanapound.wsclient.ProviderSoap;


@Configuration
public class SoapClientConfig {
	
//	Set the contextPath
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in pom.xml
		marshaller.setContextPath("wsdl.classes");
	
		return marshaller;
	}


	@Bean
	public ProviderSoap providerSoap1(Jaxb2Marshaller marshaller) {
		ProviderSoap client = new ProviderSoap();
		client.setDefaultUri("http://localhost:81/ws/score.wsdl");
		//Sets the Marshaller used by the gateway
		//Ppropety required if the marshalling functionality of WebServiceTemplate is to be used
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	//Here config others WS type SOAP
}
