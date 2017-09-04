package com.provider.ws.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * The Class WebServiceConfig.  Configure web service beans
 * 
 */
@EnableWs
@Configuration
public class WebServiceConfig{
	
	/**
	 * Message dispatcher servlet. Generate the Server
	 *
	 * @param applicationContext the application context
	 * @return the servlet registration bean
	 */
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		//With MessageDispatcherServlet spring WS detect Spring beans automatically.
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		//Set applicationContext
		servlet.setApplicationContext(applicationContext);
		//Sets whether relative address locations in the WSDL are to be transformed using the request URI of the incoming
		servlet.setTransformWsdlLocations(true); //Used when the access is from public IP address
		return new ServletRegistrationBean(servlet, "/ws/*");
	}
	
	/**
	 * Bank wsdl 11 definition.
	 *
	 * @param providerSchema the provider schema
	 * @return the default wsdl 11 definition
	 */
	@Bean(name = "score") //Name url where WSDL file is available
	//Exposes a standard WSDL 1.1 using XsdSchema
	public DefaultWsdl11Definition bankWsdl11Definition(XsdSchema providerSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		//can combine one request and one response message into a single request/response operation.
		wsdl11Definition.setPortTypeName("ProviderPort");
		
		wsdl11Definition.setLocationUri("/ws");
		
		//Enables the WSDL document to refer to itself
		wsdl11Definition.setTargetNamespace("http://provider.com/ws/model");
		wsdl11Definition.setSchema(providerSchema);
		return wsdl11Definition;
	}

	/**
	 * Provider score schema.
	 *
	 * @return the xsd schema
	 */
	@Bean
	public XsdSchema providerScoreSchema() {
		return new SimpleXsdSchema(new ClassPathResource("wsScore.xsd"));
	}
}
