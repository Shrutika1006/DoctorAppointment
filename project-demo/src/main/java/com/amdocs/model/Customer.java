package com.amdocs.model;

public class Customer {
	
	private int id;
	private String firstName;
	private String lastName;
	private String phone_no;
	
	public Customer() {
		
	}
	
	
	public Customer(int id, String firstName, String lastName, String phone_no) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone_no = phone_no;
	}

	public Customer(String firstName, String lastName, String phone_no) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone_no = phone_no;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone_no;
	}
	public void setPhone(String phone_no) {
		this.phone_no = phone_no;
	}	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", Phone Number=" + phone_no
				+ "]";
	}
	
}
