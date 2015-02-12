package com.learning.demo.spring.hibernate.demo.infra;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.learning.demo.spring.hibernate.demo.entity.Employee;
import com.learning.demo.spring.hibernate.demo.view.ExcelViewResolver;
import com.learning.demo.spring.hibernate.demo.view.Jaxb2MarshallingXmlViewResolver;
import com.learning.demo.spring.hibernate.demo.view.JsonViewResolver;
import com.learning.demo.spring.hibernate.demo.view.PdfViewResolver;

@EnableWebMvc
@ComponentScan(basePackages = { "com.learning.demo.spring.hibernate.demo" })
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
public class WebMvcDemoConfig extends WebMvcConfigurationSupport {

	@Value("${db.driver}")
	private String dbDriver;
	@Value("${db.url}")
	private String dbUrl;
	@Value("${db.username}")
	private String dbUser;
	@Value("${db.password}")
	private String dbPassword;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public ContentNegotiatingViewResolver cm(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		resolver.setOrder(0);

		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
		viewResolvers.add(new ExcelViewResolver());
		viewResolvers.add(new PdfViewResolver());
		viewResolvers.add(new Jaxb2MarshallingXmlViewResolver());
		viewResolvers.add(new JsonViewResolver());
		viewResolvers.add(getTh());
		resolver.setViewResolvers(viewResolvers);
		return resolver;
	}

	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(false);
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		super.configureContentNegotiation(configurer);
	}

	@Bean
	public ThymeleafViewResolver getTh() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCacheable(false);

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(resolver);
		viewResolver.setTemplateEngine(engine);
		String[] viewNames = { "*" };
		viewResolver.setViewNames(viewNames);
		return viewResolver;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory factory) {
		HibernateTransactionManager manager = new HibernateTransactionManager(factory);
		return manager;
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean manager = new LocalSessionFactoryBean();
		manager.setDataSource(dataSource);
		manager.setAnnotatedClasses(Employee.class);//Address.class
		return manager;
	}
}