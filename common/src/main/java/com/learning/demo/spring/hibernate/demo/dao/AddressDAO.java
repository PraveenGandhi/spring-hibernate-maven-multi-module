package com.learning.demo.spring.hibernate.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.demo.spring.hibernate.demo.entity.Address;

@Repository
public class AddressDAO implements IAddressDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Address employee) {
		sessionFactory.getCurrentSession().save(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Address> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Address.class).list();
	}

	public Address get(Long id) {
		return (Address) sessionFactory.getCurrentSession().get(Address.class, id);
	}

}
