package com.idfc.service;

import java.util.List;
import java.util.Optional;

import com.idfc.model.PatientInfo;

public interface PatientInfoService {

	public PatientInfo addPatientInfo(PatientInfo patientInfo);
	
	public Optional<PatientInfo> getInfoById(long id);
	
	public PatientInfo getPatientInfoByUserId(long userId);
	
	public List<PatientInfo> getAllPatientInfo();
	
	public PatientInfo updatePatientInfo(PatientInfo patientInfo, long id);
	
	public String deletePatientInfo(long id);
	
}
