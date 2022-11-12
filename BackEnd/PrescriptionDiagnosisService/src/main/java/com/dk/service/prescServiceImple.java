package com.dk.service;

import java.util.List;
import java.util.Optional;

import com.dk.dao.prescRepository;
import com.dk.model.Prescription;
import com.dk.model.Diagnosis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class prescServiceImple implements prescService {
	
	@Autowired
	private prescRepository repo;

	public prescServiceImple(prescRepository repo) {
		super();
		this.repo = repo;
	}

	public prescServiceImple() {
		super();
		//TODO Auto-generated constructor stub
	}

	@Override
	public Prescription addPrescription(Prescription prescription) {
		return this.repo.save(prescription);
	}
	
	@Override
	public List<Prescription> getPrescription() {
		return this.repo.findAll();
	}
	
	@Override
	public Optional<Prescription> getPrescriptionById(int prescId) {
		return this.repo.findById(prescId);
	}
	
	@Override
	public String deletePrescription(int prescId) {
		Optional<Prescription> res = repo.findById(prescId);
		if(res.get() == null) {
			return null;
		}
		this.repo.deleteById(prescId);
		return "Prescription deleted sucessfully";
	}
	
	@Override
	public Prescription updatePrescription(Prescription prescription, int patientId) {
		Prescription resPrescription = repo.findByPatientId(patientId);
		if(resPrescription == null) {
			return null;
		}
		Diagnosis resDiagnosis = resPrescription.getDiagnosis();
		Diagnosis diagnosis = prescription.getDiagnosis();
		resDiagnosis.setDiagnosisTitle(diagnosis.getDiagnosisTitle());
		resDiagnosis.setExpertComments(diagnosis.getExpertComments());
		resPrescription.setDiagnosis(resDiagnosis);
		resPrescription.setPrescDetails(prescription.getPrescDetails());
		return repo.save(resPrescription);
	}
	
	@Override
	public Prescription getPrescriptionByPatientId(int patientId) {
		return this.repo.findByPatientId(patientId);
	}
	
}