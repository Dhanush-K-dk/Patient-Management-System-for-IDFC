package com.dk.service;

import java.util.List;
import java.util.Optional;

import com.dk.model.Diagnosis;

public interface diagService {

	public Diagnosis addDiagnosis(Diagnosis diagnosis);
	
	public String deleteDiagnosis(long id);
	
	public Diagnosis updateDiagnosis(Diagnosis diagnosis,long id);
	
	public List<Diagnosis> getDiagnosis();
	
	public Optional<Diagnosis> getDiagnosisById(long id);
		
}
