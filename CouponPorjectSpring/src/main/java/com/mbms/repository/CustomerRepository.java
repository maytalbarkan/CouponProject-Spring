package com.mbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbms.model.Company;
import com.mbms.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	Customer findByCustomerName(String customerName);

	Customer findByCustomerNameAndPassword(String name, String password);
	
}
