package com.idfc.dao;

import java.util.List;

import com.idfc.model.PatientProblem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientProblemRepository extends JpaRepository<PatientProblem, Long> {
	public List<PatientProblem> findByPatientId(long patientId);
	public List<PatientProblem> findByDoctorId(long doctorId);
}
