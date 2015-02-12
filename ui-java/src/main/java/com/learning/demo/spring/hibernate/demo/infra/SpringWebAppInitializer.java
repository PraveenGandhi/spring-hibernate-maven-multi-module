package com.learning.demo.spring.hibernate.demo.infra;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	protected Class<?>[] getServletConfigClasses() {
		Class<?>[] classes = { WebMvcDemoConfig.class };
		return classes;
	}

	protected String[] getServletMappings() {
		String[] mappings = { "/" };
		return mappings;
	}
}