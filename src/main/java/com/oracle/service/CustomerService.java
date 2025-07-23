package com.oracle.service;

import java.util.List;

import com.oracle.model.Customer;

public interface CustomerService {

	public void addCustomer(Customer cust);
	public Customer findCustomerByEmail(String Email);
	public List<Customer> findAllCustomers();
	void addCustomer1(Customer cust);
}
