package com.oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.exceptions.DuplicateCustomerException;
import com.oracle.exceptions.NoSuchCustomerException;
import com.oracle.model.Customer;
import com.oracle.service.CustomerService;

@RestController
@RequestMapping(path = "customer-api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService service;
	
	//http://loacalhost:9090/customer-api
	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody Customer cust)
	{
		service.addCustomer(cust);
	    ResponseEntity<String> response = new ResponseEntity<String>("customer added successfully !!",
	    		HttpStatus.CREATED);
	    return response;
	}
	
	@GetMapping(path ="/getAllCustomers")
	public List<Customer> getAllCustomer()
	{
		List<Customer> customers = service.findAllCustomers();
		return customers;
	}
	
	
	//http://loacalhost:9090/customer-api/email/john.doe@example.com
	//http://loacalhost:9090/customer-api/email?emailId=john.doe@example.com
	@GetMapping("/email/{emailId}")
	public Customer getCustomerByEmail(@PathVariable("emailId") String Email)
	{
		return service.findCustomerByEmail(Email);
	}
	
	@ExceptionHandler(NoSuchCustomerException.class)
	public ResponseEntity<String> handleNoSuchCustomerException(NoSuchCustomerException ex) {
	    ResponseEntity<String> response = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    return response;
	}
	
	
	@ExceptionHandler(DuplicateCustomerException.class)
	public ResponseEntity<String> handleDuplicateCustomerException(DuplicateCustomerException ex) {
	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // 409
	}

}
