package com.oracle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oracle.model.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Repository
public class CustomerDaoImpl implements CustomerDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void createCustomer(Customer cust) {
		entityManager.persist(cust);
		
	}

	@Override
	public Customer readCustomerByEmail(String email) {
		
		return entityManager.find(Customer.class, email);
	}

	@Override
	public List<Customer> readAllCustomers() {
		String jpql = "SELECT c FROM Customer c";
		return entityManager.createQuery(jpql, Customer.class).getResultList();
		
		
	}
	
	@Override
	public Customer readCustomerByEmail1(String email) {
	    try {
	        return entityManager.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class)
	                .setParameter("email", email)
	                .getSingleResult();
	    } catch (jakarta.persistence.NoResultException e) {
	        return null; // No customer found
	    }
	}


}
