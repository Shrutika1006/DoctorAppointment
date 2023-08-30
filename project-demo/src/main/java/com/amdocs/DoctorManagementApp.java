package com.amdocs;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.amdocs.dao.CustomerDao;
import com.amdocs.dao.AppointDao;
import com.amdocs.dao.impl.CustomerDaoImpl;

import com.amdocs.dao.impl.AppointmentDaolmpl;
import com.amdocs.exception.AppointmentNotFoundException;
import com.amdocs.exception.CustomerNotFoundException;
import com.amdocs.model.Appointment;
import com.amdocs.model.Customer;
import com.google.protobuf.TextFormat.ParseException;

/**
 * Student App
 *
 */
public class DoctorManagementApp {

	private static CustomerDao dao = new CustomerDaoImpl();
	private static AppointDao daa = new  AppointmentDaolmpl();

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws CustomerNotFoundException, ParseException, SQLException, java.text.ParseException {
		while(true) {
			System.out.println("1. Customer");
			System.out.println("2. Appointment");
			System.out.println("3. Exit");
		int no = Integer.parseInt(scanner.nextLine());
switch(no) {
case 1:
		while (true) {
			System.out.println("************Enter Your choice************");
			System.out.println("1.Register Customer");
			System.out.println("2.Modify Customer Details");
			System.out.println("3.Delete Customer Record");
			System.out.println("4.View Single Record");
			System.out.println("5.View All Records");
			System.out.println("6.Exit");
			int ch = Integer.parseInt(scanner.nextLine());
			switch (ch) {
			case 1:
				System.out.println("****Registering Customer***");
				addCustomer();
				System.out.println("***Customer Registered***");
				break;
			case 2:
				System.out.println("**** 	Customer details Updating***");
				updateCustomer();
				System.out.println("****Updating 	Customer details Ended***");
				break;
			case 3:
				System.out.println("****Deleting Student Started***");
				deleteCustomer();
				System.out.println("*** Student Deleted***");
				break;
			case 4:
				findById();
				break;
			case 5:
				displayAllCustomer();
				break;
			case 6:
				System.exit(0);

			default:
				System.exit(0);
			}
		
				
		}
case 2:
	while (true) {
		System.out.println("************Enter Your choice************");
		System.out.println("1.Book an appointment");
		System.out.println("2.Modify Appointment Details");
		System.out.println("3.Delete an Appointment");
		System.out.println("4.View Single Record");
		System.out.println("5.View All Records");
		System.out.println("6.Exit");
		int ch = Integer.parseInt(scanner.nextLine());
		switch (ch) {
		case 1:
			System.out.println("****Booking an appointment***");
			BookAppointment();
			System.out.println("***Appointment booked***");
			break;
		case 2:
			System.out.println("****Updating Appointment Details Started***");
			UpdateAppointRecord();
			System.out.println("**** Appointment  Updated***");
			break;
		case 3:
			System.out.println("****Deleting Appointment***");
			deleteAppointment();
			System.out.println("****Appointment Ended***");
			break;
		case 4:
			findAppointmentById();
			break;
		case 5:
			DisplayAllAppointment();
			break;
		case 6:
			System.exit(0);

		default:
			System.out.println("****Please enter valid choice***");
		}
			
	

		}
	
case 3:
	System.exit(0);

	}
}
		}
	
	


	



	



	private static void UpdateAppointRecord() throws java.text.ParseException, SQLException {
		System.out.println("Enter Id:");
		int appointId=Integer.parseInt(scanner.nextLine());
		Appointment appoint=new Appointment();
		
		System.out.println("\nEnter new DoctorName:");
		String DoctorName=scanner.nextLine();
		appoint.setDoctorname(DoctorName);
	
		System.out.println("\nEnter Date in format(dd-mm-yyyy):");
		String AppointDate=scanner.nextLine();
		
		SimpleDateFormat format =new SimpleDateFormat("dd-MM-yyyy");
		Date date=format.parse(AppointDate);
		appoint.setAppointment_date(date);
		
		//System.out.println(appoint.getDoctorname());

	

		try {
			if(daa.UpdateAppointRecord(appointId,appoint)) {
				System.out.println("Customer records updated successfully");
			}
			else {
				System.out.println("Customer not found or updation failed");
			}
		}

		catch(AppointmentNotFoundException e) {}
		
		
	}












	private static void DisplayAllAppointment() {
		try {
			List<Appointment> DisplayAllAppointment = daa.DisplayAllAppointment();
			for (Appointment appoint : DisplayAllAppointment) {
				System.out.println(appoint);
			}
		} catch (SQLException e) {
		System.err.println(e);
		}
	}
		
	private static void deleteAppointment() {
		// TODO Auto-generated method stub
		
		System.out.println("\nEnter Id:");
		int appointmentId = Integer.parseInt(scanner.nextLine());
		try {
			Appointment deleteAppointment = daa.deleteAppointment(appointmentId);
			System.out.println(deleteAppointment);
		} catch (AppointmentNotFoundException | SQLException e) {
			System.err.println(e);
		}


	}

	
	private static void findAppointmentById() throws CustomerNotFoundException {
		System.out.println("\nEnter Id:");
		int appointId = Integer.parseInt(scanner.nextLine());
		try {
			Appointment findById = daa.findAppointmentById(appointId);
			System.out.println(findById);
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	
	private static void BookAppointment() throws ParseException, SQLException, java.text.ParseException {
		// TODO Auto-generated method stub
		System.out.println("\nEnter Id:");
		   int appointmentId = Integer.parseInt(scanner.nextLine());
			System.out.println("\nEnter Doctor Name:");
			String doctorName = scanner.nextLine();
			System.out.println("\nEnter Date:");
			String appointmentDate = scanner.nextLine();
			SimpleDateFormat format =new SimpleDateFormat("dd-MM-yyyy");
			Date date=format.parse(appointmentDate);
			
			Appointment appoint = new Appointment(appointmentId,doctorName,date);
			try {
				if(daa.booking(appoint)) {
					System.out.println("Inserted");
				}
			} catch (SQLException e) {
				System.err.println(e);
			}
			
		}



	private static void updateCustomer() {
		System.out.println("Enter Id:");
		int customerId=Integer.parseInt(scanner.nextLine());
		Customer  customer=new Customer();
		
		System.out.println("\nEnter new FirstName:");
		String FirstName=scanner.nextLine();
		customer.setFirstName(FirstName);
	
		System.out.println("\nEnter new Last Name:");
		String LastName=scanner.nextLine();
		customer.setLastName(LastName);

		System.out.println("\nEnter the New Phoneno:");
		String Phoneno=scanner.nextLine();	
		customer.setPhone(Phoneno);

		try {
			if(dao.updateCustomer(customerId,customer)) {
				System.out.println("Customer records updated successfully");
			}
			else {
				System.out.println("Customer not found or updation failed");
			}
		}

			catch(CustomerNotFoundException|SQLException e) {}
		
		
	}





	private static void deleteCustomer() {
		// TODO Auto-generated method stub
		
		System.out.println("\nEnter Id:");
		int customerId = Integer.parseInt(scanner.nextLine());
		try {
			//Customer deleteCustomer = dao.deleteCustomer(customerId);,
			if(dao.deleteCustomer(customerId)) {
			System.out.println("Customer deleted successfully");
			}
			else {
				System.out.println("Customer not found");
				
			}
			
		} catch (CustomerNotFoundException | SQLException e) {
			System.err.println(e);
		}
		
	}







	private static void addCustomer() {
		System.out.println("\nEnter Id:");
	   int id = Integer.parseInt(scanner.nextLine());
		System.out.println("\nEnter First Name:");
		String firstName = scanner.nextLine();
		System.out.println("\nEnter Last Name:");
		String lastName = scanner.nextLine();
		System.out.println("\nEnter Phone Number:");
		 String phone_no = scanner.nextLine();
		Customer customer = new Customer(id,firstName, lastName, phone_no);
		try {
			if(dao.insert(customer)) {
				System.out.println("Inserted");
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}

	private static void findById() {
		System.out.println("\nEnter Id:");
		int customerId = Integer.parseInt(scanner.nextLine());
		try {
			Customer findById = dao.findById(customerId);
			System.out.println(findById);
		} catch (CustomerNotFoundException | SQLException e) {
			System.err.println(e);
		}
	}
//private static void updateCustomer() throws StudentNotFoundException {
//		
//	
//	System.out.println("\nEnter Id:");
//	int studentId = Integer.parseInt(scanner.nextLine());
//		System.out.println("\nEnter First Name:");
//		String firstName = scanner.nextLine();
//		System.out.println("\nEnter Last Name:");
//		String lastName = scanner.nextLine();
//		System.out.println("\nEnter Phone Number:");
//		 String phone_no = scanner.nextLine();
//	Student student = new Student(studentId,firstName, lastName, phone_no);
//	try {
//	if(dao.updateCustomer(studentId)) {
//	
//	
//			System.out.println("Updated");
//		}
//	} catch (SQLException e) {
//		System.err.println(e);
//	}
//	
//	try {
//		boolean updateCustomer = dao.updateCustomer(studentId);
//		System.out.println(updateCustomer);
//	} catch (StudentNotFoundException | SQLException e) {
//		System.err.println(e);
//	}
//	
//}
//		
		
		// TODO Auto-generated method stub
		
	
//	 private static void update() {
//	        System.out.println("\nEnter Id:");
//	        int studentId= Integer.parseInt(scanner.nextLine());
//	        
////	        Student student=new Student();
//	        
//	        System.out.println("\nEnter New First Name:");
//	        String firstName = scanner.nextLine();
//
//	        System.out.println("\nEnter New Last Name:");
//	        String lastName = scanner.nextLine();
//	        
//	        System.out.println("\nEnter New Phone No:");
//	        String phone_no = scanner.nextLine();
//	        
//	        try { 
//	        	Student student= dao.findById(studentId); 
//	        student.setFirstName(firstName);
//	        student.setLastName(lastName);
//	        student.setPhone(phone_no);
//	 if (dao.update(student)) 
//	{ System.out.println("First Name Update Successfully");
//	            }
//	        } catch (StudentNotFoundException | SQLException e) {
//	            System.err.println(e);
//	        }
//	    }


	private static void displayAllCustomer() {
		try {
			List<Customer> displayAllCustomer = dao.displayAllCustomer();
			for (Customer customer : displayAllCustomer) {
				System.out.println(customer);
			}
		} catch (SQLException e) {
		System.err.println(e);
		}
	}
}
