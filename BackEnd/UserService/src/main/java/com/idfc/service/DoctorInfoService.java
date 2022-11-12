package com.idfc.service;

import java.util.List;
import java.util.Optional;

import com.idfc.model.DoctorInfo;

public interface DoctorInfoService {

	public DoctorInfo addDoctorInfo(DoctorInfo doctorInfo);
	
	public Optional<DoctorInfo> getInfoById(long id);
	
	public DoctorInfo getDoctorInfoByUserId(long userId);
	
	public List<DoctorInfo> getAllDoctorInfo(String specialization);
	
	public DoctorInfo updateDoctorInfo(DoctorInfo doctorInfo, long id);
	
	public String deleteDoctorInfo(long id);
}
