package com.idfc.service;

import java.util.List;
import java.util.Optional;

import com.idfc.model.PatientProblem;


public interface PatientProblemService {

	public PatientProblem addPatientProblem(PatientProblem patientProblem);
	
	public List<PatientProblem> getPatientProblem();
	
	public Optional<PatientProblem> getPatientProblemById(long id);
	
	public List<PatientProblem> getPatientProblemByPatientId(long id);
	
	public List<PatientProblem> getPatientProblemByDoctorId(long doctorId);
	
	public PatientProblem updatePatientProblem(PatientProblem patientProblem, long patientId);
	
	public String deletePatientProblem(long patientId);
}
