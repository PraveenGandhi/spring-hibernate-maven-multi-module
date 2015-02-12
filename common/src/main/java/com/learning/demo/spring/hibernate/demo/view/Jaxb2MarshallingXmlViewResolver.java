package com.learning.demo.spring.hibernate.demo.view;

import java.util.Locale;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.learning.demo.spring.hibernate.demo.entity.Employee;

public class Jaxb2MarshallingXmlViewResolver implements ViewResolver {

	private Marshaller marshaller;

	public Jaxb2MarshallingXmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Employee.class);
		this.marshaller = marshaller;
	}

	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MarshallingView view = new MarshallingView();
		view.setMarshaller(marshaller);
		return view;
	}
}
