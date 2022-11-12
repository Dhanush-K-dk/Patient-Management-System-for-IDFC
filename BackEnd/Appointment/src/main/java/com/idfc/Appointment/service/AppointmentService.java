package com.idfc.Appointment.service;

import java.util.List;

import com.idfc.Appointment.model.Appointment;


public interface AppointmentService {
	
	public Appointment addAppointment(Appointment appointment);
	
	public List<Appointment> getAppointment();
	
	public Appointment getAppointmentById(int appointmentId);
	
	public Appointment getAppointmentByPatientId(int patientId);
	
	public List<Appointment> getAppointmentByDoctorId(int doctorId);
	
//	public Appointment updateAppointment(int appointmentId, String patientId);
	
	public Appointment updateAppointmentDate(int appointmentId, String appointmentDate);
	
	public Appointment updateAppointmentTime(int appointmentId, String appointmentTime);
	
	public Appointment updateAppointment(Appointment appointment,int appointmentId);
	
	public String deleteAppointment(int appointmentId);
	
	public Object checkAppointmentAvailable(String date, int docotorId);

}
