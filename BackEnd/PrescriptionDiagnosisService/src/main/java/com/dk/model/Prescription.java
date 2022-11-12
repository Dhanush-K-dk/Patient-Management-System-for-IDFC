package com.dk.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "prescription_details")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int prescId;

	@Column(nullable = false)
	private int patientId;
	
	@Column(nullable = false)
	private int patientProblemId;
	
	@Column(nullable = false)
	private String prescDetails;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Diagnosis diagnosis;
	
	public Prescription() {
		super();
		//TODO Auto-generated constructor stub
	}

	public Prescription(int prescId, int patientId, int patientProblemId, String prescDetails, Diagnosis diagnosis) {
		super();
		this.prescId = prescId;
		this.patientId = patientId;
		this.patientProblemId = patientProblemId;
		this.prescDetails = prescDetails;
		this.diagnosis = diagnosis;
	}

	public int getPrescId() {
		return prescId;
	}

	public void setPrescId(int prescId) {
		this.prescId = prescId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getPatientProblemId() {
		return patientProblemId;
	}

	public void setPatientProblemId(int patientProblemId) {
		this.patientProblemId = patientProblemId;
	}

	public String getPrescDetails() {
		return prescDetails;
	}

	public void setPrescDetails(String prescDetails) {
		this.prescDetails = prescDetails;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	
	
}
