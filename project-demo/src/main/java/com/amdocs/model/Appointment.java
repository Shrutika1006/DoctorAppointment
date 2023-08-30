package com.amdocs.model;

import java.util.Date;

public class Appointment {
	private int appointmentId;
	private String doctorName;
	private Date appointmentDate ; 

	/*
	 * public Appointment(int appointmentId, String doctorName, Date
	 * appointmentDate) { this.appointmentId=appointmentId;
	 * this.doctorName=doctorName; this.appointmentDate=appointmentDate; }
	 */
	
	
	
	public int getAppointment_id() {
		return appointmentId;
	}

	public Appointment(String doctorName, Date appointmentDate) {
		super();
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
	}

	public Appointment(int appointmentId, String doctorName, Date appointmentDate) {
		super();
		this.appointmentId = appointmentId;
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
	}

	public Appointment() {
		// TODO Auto-generated constructor stub
	}

	public void setAppointment_id(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getDoctorname() {
		return doctorName;
	}
	public void setDoctorname(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getAppointment_date() {
		return appointmentDate;
	}
	public void setAppointment_date(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	@Override
	public String toString() {
		return "Student [id=" +appointmentId + ", firstName=" + doctorName + ", lastName=" + appointmentDate 
				+ "]";
	}

}
