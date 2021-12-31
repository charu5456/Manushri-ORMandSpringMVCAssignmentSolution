package com.customerRelationship.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.customerRelationship.entity.Customer;

@Service
public interface customerService {
	
	List <Customer> findAll();
	Customer findById(int customerId);
	void saveCustomer(Customer newCustomer);
	void deleteById(int customerId);
}