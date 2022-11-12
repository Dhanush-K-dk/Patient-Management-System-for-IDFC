package com.idfc.dao;

import java.util.List;

import com.idfc.model.DoctorInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorInfoRepository extends JpaRepository<DoctorInfo, Long> {
	
	public DoctorInfo findByUserId(long id);
	
	public List<DoctorInfo> findBySpecialization(String specialization);
}
