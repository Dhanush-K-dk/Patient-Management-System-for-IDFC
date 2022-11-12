package com.idfc.dao;

import com.idfc.model.PatientInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long> {

	public PatientInfo findByUserId(long id);
}
