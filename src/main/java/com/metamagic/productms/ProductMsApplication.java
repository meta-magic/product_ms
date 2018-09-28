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
		String user = System.getenv("mysqluser");
		String password = System.getenv("mysqlpassword");
		String connectionurl = System.getenv("mysqlconnectionurl");
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
