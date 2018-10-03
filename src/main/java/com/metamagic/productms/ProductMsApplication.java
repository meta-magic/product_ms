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

import com.metamagic.productms.dto.VersionInfo;

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
	public VersionInfo getVersion(){
		String version = System.getenv("PRODUCTMS_VERSION");
		System.out.println("*****version******"+version);
		if(version==null){
			version = " ";
		}
		return new VersionInfo(version);
	}
	
	@Bean
	public PersistenceManagerFactory getPersistenceManagerFactory() {
		String user = System.getenv("PRODUCTMS_DB_USER");
		String password = System.getenv("PRODUCTMS_DB_PASSWORD");
		String connectionurl = System.getenv("PRODUCTMS_DB_CONNECTION_URL");
		System.out.println(user+"--"+password+"--"+connectionurl+"---");
		Properties prop = new Properties();
		prop.setProperty("datanucleus.ConnectionURL","jdbc:mysql://mysql/sys");
		prop.setProperty("javax.jdo.option.ConnectionDriverName","com.mysql.jdbc.Driver");
		prop.setProperty("javax.jdo.option.ConnectionUserName",user);
		prop.setProperty("javax.jdo.option.ConnectionPassword",password);
		prop.setProperty("datanucleus.schema.autoCreateAll", "true");
		prop.setProperty("datanucleus.schema.validateConstraints", "false");
		prop.setProperty("datanucleus.schema.validateTables", "false");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(prop);
		return pmf;
	}
}
