package com.amdocs.dao;

import java.sql.SQLException;
import java.util.List;

import com.amdocs.exception.CustomerNotFoundException;
import com.amdocs.model.Appointment;
import com.amdocs.model.Customer;

public interface CustomerDao {
	
	


	List<Customer> displayAllCustomer() throws SQLException;
	
	boolean deleteCustomer(int customerId) throws CustomerNotFoundException, SQLException; 
	
	
	boolean insert(Customer customer) throws SQLException;

	boolean updateCustomer(int customerId, Customer updateCustomer) throws SQLException,CustomerNotFoundException;

	Customer findById(int customerId) throws CustomerNotFoundException, SQLException;



	
	
}
