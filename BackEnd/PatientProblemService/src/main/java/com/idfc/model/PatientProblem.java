package com.idfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "patient_problem_details")
public class PatientProblem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private long patientId;
	
	@Column(nullable = false)
	private String symptoms;
	
	@Column(nullable = false)
	private String pastMedHist;
	
	@Column(nullable = false)
	private long doctorId;

	public PatientProblem() {
		super();
		//TODO Auto-generated constructor stub
	}

	public PatientProblem(long id, long patientId, String symptoms, String pastMedHist, long doctorId) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.symptoms = symptoms;
		this.pastMedHist = pastMedHist;
		this.doctorId = doctorId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getPastMedHist() {
		return pastMedHist;
	}

	public void setPastMedHist(String pastMedHist) {
		this.pastMedHist = pastMedHist;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	
	
	
	
}
