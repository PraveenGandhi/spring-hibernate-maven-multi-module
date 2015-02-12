package com.learning.demo.spring.hibernate.demo.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class ExcelViewResolver implements ViewResolver {

	public View resolveViewName(String viewName, Locale locale) throws Exception {
		ExcelView view = new ExcelView();
		return view;
	}
}