package com.learning.demo.spring.hibernate.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.demo.spring.hibernate.demo.dao.IEmployeeDAO;
import com.learning.demo.spring.hibernate.demo.entity.Employee;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeDAO employeeDAO;

	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	public List<Employee> getAll() {
		return employeeDAO.getAll();
	}

	public Employee get(Long id) {
		return employeeDAO.get(id);
	}
}
