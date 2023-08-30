package com.amdocs.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.model.Appointment;
import com.amdocs.model.Customer;
import com.amdocs.util.DbUtil;
import com.amdocs.dao.AppointDao;
import com.amdocs.exception.AppointmentNotFoundException;
import com.amdocs.exception.CustomerNotFoundException;

public class AppointmentDaolmpl implements AppointDao {
	private final static String INSERT = "insert into appointment values(?,?,?)";
	private final static String SELECT_BY_ID = "SELECT * FROM appointment WHERE appointmentId=?";
	private final static String SELECT_ALL="SELECT * FROM APPOINTMENT";
	private final static String Update =" update appointment set doctorName=?,appointmentDate=? where appointmentId=?";
	private final static String DELETE_BY_ID = "DELETE  FROM appointment WHERE appointmentId=?";
	
	private static Connection connection = DbUtil.getConnection();
	
	
	public boolean booking(Appointment appoint) throws SQLException {
		boolean result=false;
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1,appoint.getAppointment_id() );
		
		stmt.setString(2,appoint.getDoctorname() );
		stmt.setDate(3,new java.sql.Date(appoint.getAppointment_date().getTime()));
		if(stmt.executeUpdate()>0) {
			result = true;
		}
		stmt.close();
		return result;
	}

	public boolean bookingAppointment(Appointment appoint) throws SQLException{
	
	return false;
}

	@Override
//	public List<Appointment> DisplayAllAppointment() throws SQLException {
//		List<Appointment> appoints = new ArrayList<>();
//		Statement stmt = connection.createStatement();
//		ResultSet rs = stmt.executeQuery(SELECT_ALL);
//		while (rs.next()) {
//			Appointment appoint = new Appointment();
//			// Reading the data from the row and seting the data to student object
//			appoint.setAppointment_id(rs.getInt("appointmentId"));
//			appoint.setAppointment_date(rs.getDate("doctorName"));
//			appoint.setDoctorname(rs.getString("appointDate"));
//			
//			// Adding to the list
//			appoints.add(appoint);		}
//		rs.close();
//		stmt.close();
//		return appoints;
//	}
	
	
	
	public List<Appointment> DisplayAllAppointment() throws SQLException {
		List<Appointment> appoints = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL);
		while (rs.next()) {
			Appointment appoint = new Appointment();

			// Reading the data from the row and seting the data to student object
			appoint.setAppointment_id(rs.getInt("appointmentId"));
			appoint.setDoctorname(rs.getString("doctorName"));
			appoint.setAppointment_date(rs.getDate("appointmentDate"));
			
			// Adding to the list
			appoints.add(appoint);		
			}
		rs.close();
		stmt.close();
		return appoints;
	}

	
public Appointment deleteAppointment(int appointmentId) throws SQLException {

		

		Appointment appoint=null;

		PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID);

		stmt.setInt(1, appointmentId);

		int rowsAffected=stmt.executeUpdate();

		if(rowsAffected>0) {

			System.out.println("Successfully Deleted");

		}

		else {

			System.out.println("Not Found with ID"+ appointmentId + "not found");
		}

		stmt.close();

		return appoint;

	}

public Appointment findAppointmentById(int appointmentId) throws SQLException{
	Appointment appoint =null;
	PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
	stmt.setInt(1, appointmentId);
	ResultSet rs = stmt.executeQuery();
	//System.out.println("Rs  : "  + rs);
	if(rs.next()) {
		appoint = new Appointment();
		// Reading the data from the row and seting the data to student object
		
		appoint.setAppointment_id(rs.getInt("AppointmentId"));
		appoint.setDoctorname(rs.getString("DoctorName"));
		appoint.setAppointment_date(rs.getDate("AppointmentDate"));			
	}else {
	System.out.println("Customer Not Found With Id: " + appointmentId);
	}
	rs.close();
	stmt.close();
	return appoint;
	
}

@Override
public boolean UpdateAppointRecord(int appointId, Appointment UpdateAppointRecord) throws AppointmentNotFoundException {
	try(PreparedStatement stmt=connection.prepareStatement(SELECT_BY_ID)){
		stmt.setInt(1, appointId);
		ResultSet rs=stmt.executeQuery();
		
		if(rs.next()) {
			PreparedStatement updateState=connection.prepareStatement(Update);
//			updateState.setInt(1,UpdateAppointRecord.getAppointment_id());
			updateState.setString(1, UpdateAppointRecord.getDoctorname());
			updateState.setDate(2, new java.sql.Date(UpdateAppointRecord.getAppointment_date().getTime()));
			updateState.setInt(3,appointId);
		
			
			
			int rowsaffected=updateState.executeUpdate();
			updateState.close();
			
			return rowsaffected>0;
		}else {
			
			throw new AppointmentNotFoundException("customer not found with id:"+appointId);
		}
	}catch(SQLException e) {}
	return false;
}


	
	

//	@Override
//	public boolean updateCustomer(int customerId, Student updateCustomer)
//			throws SQLException, StudentNotFoundException {
//		// TODO Auto-generated method stub
//		return false;
//	}


	

	
}

