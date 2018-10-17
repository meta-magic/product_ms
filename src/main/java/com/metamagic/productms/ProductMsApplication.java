package com.metamagic.productms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.metamagic.productms.dto.VersionInfo;
import com.uber.jaeger.Tracer.Builder;
import com.uber.jaeger.metrics.Metrics;
import com.uber.jaeger.metrics.NullStatsReporter;
import com.uber.jaeger.metrics.StatsFactoryImpl;
import com.uber.jaeger.propagation.b3.B3TextMapCodec;
import com.uber.jaeger.reporters.RemoteReporter;
import com.uber.jaeger.samplers.ConstSampler;
import com.uber.jaeger.senders.HttpSender;

import io.opentracing.propagation.Format.Builtin;

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
		String user = System.getenv("PRODUCTMS_DB_USER");
		String password = System.getenv("PRODUCTMS_DB_PASSWORD");
		String connectionurl = System.getenv("PRODUCTMS_DB_CONNECTION_URL");
		System.out.println(user+"--"+password+"--"+connectionurl+"---");
		
		String version = System.getenv("PRODUCTMS_VERSION");
		System.out.println("*****version******"+version);
		if(version==null){
			version = " ";
		}
		return new VersionInfo(version);
	}
	
	@Bean
	  public io.opentracing.Tracer jaegerTracer() {
	    Builder builder = new Builder("spring-boot",
	        new RemoteReporter(new HttpSender("http://jaeger-collector.istio-system:14268/api/traces"), 10,
	        65000, new Metrics(new StatsFactoryImpl(new NullStatsReporter()))),
	        new ConstSampler(true))
	        .registerInjector(Builtin.HTTP_HEADERS, new B3TextMapCodec())
	        .registerExtractor(Builtin.HTTP_HEADERS, new B3TextMapCodec());
	    return builder.build();
	  }
}
