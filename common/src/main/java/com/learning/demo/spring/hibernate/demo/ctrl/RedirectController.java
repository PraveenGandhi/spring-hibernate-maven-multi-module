package com.learning.demo.spring.hibernate.demo.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RedirectController {
	@RequestMapping
	public String get() {
		return "redirect:employees/getAll";
	}
}
