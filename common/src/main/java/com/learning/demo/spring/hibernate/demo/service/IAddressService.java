package com.learning.demo.spring.hibernate.demo.service;

import java.util.List;

import com.learning.demo.spring.hibernate.demo.entity.Address;

public interface IAddressService {

	public abstract void save(Address employee);

	public abstract List<Address> getAll();

	public abstract Address get(Long id);

}