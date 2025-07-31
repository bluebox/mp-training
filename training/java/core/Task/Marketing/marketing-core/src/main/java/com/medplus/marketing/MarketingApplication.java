package com.medplus.marketing;

import javax.servlet.http.HttpSessionEvent;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.medplus.activemq.aop.EnableAMQ;
import com.medplus.activemq.config.AMQProperties;

@ImportResource(locations = { "classpath:applicationContext.xml"})
@ComponentScan(basePackages = { "com.medplus", "com.optival", "db.test.datasource.config" }, excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.medplus\\.chat..*")})
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		TransactionAutoConfiguration.class,
		JmxAutoConfiguration.class,
		FreeMarkerAutoConfiguration.class,
		PersistenceExceptionTranslationAutoConfiguration.class,
		EmbeddedWebServerFactoryCustomizerAutoConfiguration.class,
		SecurityAutoConfiguration.class,
		SolrAutoConfiguration.class,
		JmsAutoConfiguration.class
})
@EnableScheduling
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled= true)
@EnableTransactionManagement
@EnableConfigurationProperties(AMQProperties.class)
@EnableAMQ
public class MarketingApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(MarketingApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MarketingApplication.class);
	}
	
	@EventListener
	public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(HttpSessionEvent event) {
		return new SingleSignOutHttpSessionListener();
	}

}
