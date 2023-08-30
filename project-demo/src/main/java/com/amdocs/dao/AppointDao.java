package com.amdocs.dao;

import java.sql.SQLException;
import java.util.List;

import com.amdocs.exception.AppointmentNotFoundException;
import com.amdocs.exception.CustomerNotFoundException;
import com.amdocs.model.Appointment;
//import com.amdocs.model.Student;
import com.amdocs.model.Customer;

public interface AppointDao {

	
	//Student findById(int studentId) throws StudentNotFoundException, SQLException; 
	
	//Student updateCustomer(int studentId) throws StudentNotFoundException, SQLException; 
	
	//Student deleteCustomer(int studentId) throws StudentNotFoundException, SQLException; 
	
	
	boolean bookingAppointment(Appointment appoint) throws SQLException;
	
	boolean booking(Appointment student) throws SQLException;

	List<Appointment> DisplayAllAppointment() throws SQLException;
	
//	boolean UpdateAppointment(int appointId, Appointment updateAppointment) throws SQLException,StudentNotFoundException;
	
	Appointment deleteAppointment(int appointmentId) throws AppointmentNotFoundException, SQLException;
	
	Appointment findAppointmentById(int appointmentId) throws SQLException;

	boolean UpdateAppointRecord(int appointId, Appointment UpdateAppointRecord) throws AppointmentNotFoundException;

	

}
