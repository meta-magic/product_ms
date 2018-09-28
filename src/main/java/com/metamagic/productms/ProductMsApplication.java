package com.metamagic.productms;

import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductMsApplication extends SpringBootServletInitializer{

	
	public static void main(String[] args) {
		SpringApplication.run(ProductMsApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProductMsApplication.class);
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public PersistenceManagerFactory getPersistenceManagerFactory() {
		String user = System.getenv("PRODUCTMS_DB_USER");
		String password = System.getenv("PRODUCTMS_DB_PASSWORD");
		String connectionurl = System.getenv("PRODUCTMS_DB_CONNECTION_URL");
		System.out.println(user+"--"+password+"--"+connectionurl);
		Properties prop = new Properties();
		prop.setProperty("datanucleus.ConnectionURL","jdbc:mysql://mysql/sys");
		prop.setProperty("javax.jdo.option.ConnectionDriverName","com.mysql.jdbc.Driver");
		prop.setProperty("javax.jdo.option.ConnectionUserName","root");
		prop.setProperty("javax.jdo.option.ConnectionPassword","root");
		prop.setProperty("datanucleus.schema.autoCreateAll", "true");
		prop.setProperty("datanucleus.schema.validateConstraints", "false");
		prop.setProperty("datanucleus.schema.validateTables", "false");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(prop);
		return pmf;
	}
}
