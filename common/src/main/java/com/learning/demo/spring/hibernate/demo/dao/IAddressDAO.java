package com.learning.demo.spring.hibernate.demo.dao;

import java.util.List;

import com.learning.demo.spring.hibernate.demo.entity.Address;

public interface IAddressDAO {

	public abstract void save(Address employee);
	public abstract List<Address> getAll();
	public abstract Address get(Long id);

}