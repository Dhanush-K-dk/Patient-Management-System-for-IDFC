package com.dk.service;

import java.util.List;
import java.util.Optional;

import com.dk.model.Prescription;

public interface prescService {

	public Prescription addPrescription(Prescription prescription);
	
	public String deletePrescription(int prescId);
	
	public Prescription updatePrescription(Prescription prescription, int patientId);
	
	public List<Prescription> getPrescription();
	
	public Optional<Prescription> getPrescriptionById(int prescId);
	
	public Prescription getPrescriptionByPatientId(int patientId);
		
}
