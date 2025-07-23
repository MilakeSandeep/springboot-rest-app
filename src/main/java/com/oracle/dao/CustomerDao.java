package com.oracle.dao;



import java.util.List;

import com.oracle.model.Customer;

public interface CustomerDao {

	public void createCustomer(Customer cust);
	public Customer readCustomerByEmail1(String email);
	public List<Customer> readAllCustomers();
	Customer readCustomerByEmail(String email);

}
