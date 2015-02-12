package com.learning.demo.spring.hibernate.demo.service;

import java.util.List;

import com.learning.demo.spring.hibernate.demo.entity.Employee;

public interface IEmployeeService {

	public abstract void save(Employee employee);

	public abstract List<Employee> getAll();

	public abstract Employee get(Long id);

}