package com.dk.service;

import java.util.List;
import java.util.Optional;

import com.dk.dao.diagRepository;
import com.dk.model.Diagnosis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class diagServiceImple implements diagService {
	
	@Autowired
	private diagRepository repo;

	public diagServiceImple(diagRepository repo) {
		super();
		this.repo = repo;
	}

	public diagServiceImple() {
		super();
		//TODO Auto-generated constructor stub
	}

	@Override
	public Diagnosis addDiagnosis(Diagnosis diagnosis) {
		return repo.save(diagnosis);
	}
	
	@Override
	public List<Diagnosis> getDiagnosis() {
		return this.repo.findAll();
	}
	
	@Override
	public Optional<Diagnosis> getDiagnosisById(long id) {
		return this.repo.findById(id);   
	}
	
	@Override
	public String deleteDiagnosis(long id) {
		Optional<Diagnosis> resDiagnosis = repo.findById(id);
		if(resDiagnosis.get() == null) {
			return null;
		}
		this.repo.deleteById(id);
		return "Diagnosis deleted sucessfully";
	}
	
	@Override
	public Diagnosis updateDiagnosis(Diagnosis diagnosis, long id) {
		Optional<Diagnosis> resDiagnosis = repo.findById(id);
		if(resDiagnosis.get() == null) {
			return null;
		}
		resDiagnosis.get().setDiagnosisTitle(diagnosis.getDiagnosisTitle());
		resDiagnosis.get().setExpertComments(diagnosis.getExpertComments());
		return this.repo.save(resDiagnosis.get());
	}
}