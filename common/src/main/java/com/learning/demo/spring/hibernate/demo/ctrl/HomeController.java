package com.learning.demo.spring.hibernate.demo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learning.demo.spring.hibernate.demo.entity.Employee;
import com.learning.demo.spring.hibernate.demo.service.IEmployeeService;

@Controller
@RequestMapping("/employees")
public class HomeController {

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping(value = "/getAll")
	public void index(ModelMap map) {
		map.addAttribute("employees", employeeService.getAll());
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void create(ModelMap map) {
		map.addAttribute("employee", new Employee());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(@ModelAttribute Employee employee) {
		employeeService.save(employee);
		return "redirect:getAll";
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public String get(@PathVariable Long id, ModelMap map) {
		map.addAttribute("employee", employeeService.get(id));
		return "employees/display";
	}

}
