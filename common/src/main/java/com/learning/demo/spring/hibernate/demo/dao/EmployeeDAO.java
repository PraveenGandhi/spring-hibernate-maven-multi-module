package com.learning.demo.spring.hibernate.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.demo.spring.hibernate.demo.entity.Employee;

@Repository
public class EmployeeDAO implements IEmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Employee employee) {
		sessionFactory.getCurrentSession().save(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Employee.class).addOrder(Order.asc("id")).list();
	}

	public Employee get(Long id) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
	}

}
