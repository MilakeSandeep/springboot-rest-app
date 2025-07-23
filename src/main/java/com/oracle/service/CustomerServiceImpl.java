package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.CustomerDao;
import com.oracle.exceptions.DuplicateCustomerException;
import com.oracle.exceptions.NoSuchCustomerException;
import com.oracle.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;

    // Adds a customer, throws exception if email already exists
    @Override
    public void addCustomer(Customer cust) {
        Customer existing = dao.readCustomerByEmail(cust.getEmail());
        if (existing != null) {
            throw new DuplicateCustomerException("Customer with email " + cust.getEmail() + " already exists");
        }
        dao.createCustomer(cust);
    }

    // Find customer by email, throw if not found
    @Override
    public Customer findCustomerByEmail(String email) {
        Customer customer = dao.readCustomerByEmail(email);
        if (customer == null) {
            throw new NoSuchCustomerException("Customer with email " + email + " not found");
        }
        return customer;
    }

    // Return all customers
    @Override
    public List<Customer> findAllCustomers() {
        return dao.readAllCustomers();
    }

	@Override
	public void addCustomer1(Customer cust) {
		// TODO Auto-generated method stub
		
	}
}
