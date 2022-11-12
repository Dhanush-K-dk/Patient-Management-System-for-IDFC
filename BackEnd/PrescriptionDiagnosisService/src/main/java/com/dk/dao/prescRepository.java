package com.dk.dao;

import com.dk.model.Prescription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface prescRepository extends JpaRepository<Prescription, Integer> {
	
	public Prescription findByPatientId(int patientId);

}
