package com.amdocs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.dao.CustomerDao;
import com.amdocs.exception.CustomerNotFoundException;
import com.amdocs.model.Customer;
import com.amdocs.util.DbUtil;

public class CustomerDaoImpl implements CustomerDao {
	private final static String SELECT_ALL = "SELECT * FROM CUSTOMER";
	private final static String SELECT_BY_ID = "SELECT * FROM CUSTOMER WHERE id=?";
	private final static String DELETE_BY_ID = "DELETE  FROM CUSTOMER WHERE id=?";
	private final static String INSERT = "insert into customer values(?,?,?,?)";
	private final static String Update =" update customer set firstname=?,lastname=?,phone_no=? where id=?";
	private static Connection connection = DbUtil.getConnection();

	@Override
	public List<Customer> displayAllCustomer() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL);
		while (rs.next()) {
			Customer customer = new Customer();
			// Reading the data from the row and seting the data to student object
			customer.setId(rs.getInt("id"));
			customer.setFirstName(rs.getString("firstname"));
			customer.setLastName(rs.getString("lastname"));
			customer.setPhone(rs.getString("phone_no"));
			// Adding to the list
			customers.add(customer);		}
		rs.close();
		stmt.close();
		return customers;
	}

	@Override
	public Customer findById(int customerId) throws CustomerNotFoundException, SQLException {
		Customer customer =null;
		PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		//System.out.println("Rs  : "  + rs);
		if(rs.next()) {
			customer = new Customer();
			// Reading the data from the row and seting the data to student object
			customer.setId(rs.getInt("id"));
			customer.setFirstName(rs.getString("firstname"));
			customer.setLastName(rs.getString("lastname"));
			customer.setPhone(rs.getString("phone_no"));			
		}else {
			throw new CustomerNotFoundException("Student Not Found With Id: " + customerId);
		}
		rs.close();
		stmt.close();
		return customer;
		
	}
public boolean deleteCustomer(int customerId) throws  SQLException {

	
	try(PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID)){
		stmt.setInt(1, customerId);
		return stmt.executeUpdate()>0;
	}catch(SQLException e){
		e.printStackTrace();
	}
	
				return false;

	}

	

	@Override
	public boolean insert(Customer customer) throws SQLException {
		boolean result=false;
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1,customer.getId() );
		
		stmt.setString(2,customer.getFirstName() );
		stmt.setString(3,customer.getLastName() );
		stmt.setString(4,customer.getPhone() );
		if(stmt.executeUpdate()>0) {
			result = true;
		}
		stmt.close();
		return result;
	}

	@Override
	public boolean updateCustomer(int customerId, Customer updateCustomer) throws SQLException,CustomerNotFoundException {
		try(PreparedStatement stmt=connection.prepareStatement(SELECT_BY_ID)){
			stmt.setInt(1, customerId);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				PreparedStatement updateState=connection.prepareStatement(Update);
				updateState.setString(1,updateCustomer.getFirstName());
				updateState.setString(2, updateCustomer.getLastName());
				updateState.setString(3, updateCustomer.getPhone());
				updateState.setInt(4, customerId);
				
				
				int rowsaffected=updateState.executeUpdate();
				updateState.close();
				
				return rowsaffected>0;
			}else {
				
				throw new CustomerNotFoundException("customer not found with id:"+customerId);
			}
		}catch(CustomerNotFoundException|
				SQLException e) {}
		return false;
		
	}

	


	

}

