package com.idfc.Appointment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.idfc.Appointment.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>  {
	
	public List<Appointment> findByDoctorId(int doctorId);
	public Appointment findByPatientId(int patientId);
	public List<Appointment> findByAppointmentDateAndDoctorId(String date, int doctorId);
}
