package com.sage.loanapound.ws.configuration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class TestSoapClientConfig extends SoapClientConfig{
	@Mock
	Jaxb2Marshaller jaxb2Marshaller;
	
	@Test
	public void testMarshaller() {
		assertEquals("wsdl.classes", marshaller().getContextPath());
	}

	@Test
	public void testProviderSoap() {
		assertEquals("http://localhost:81/ws/score.wsdl", providerSoap(jaxb2Marshaller).getDefaultUri());
	}

}
