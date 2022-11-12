package com.idfc.service;

import java.util.List;
import java.util.Optional;

import com.idfc.dao.PatientInfoRepository;
import com.idfc.model.PatientInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientInfoServiceImpl implements PatientInfoService {

	@Autowired
	private PatientInfoRepository repo;
	
	@Override
	public PatientInfo addPatientInfo(PatientInfo patientInfo) {
		return repo.save(patientInfo);
	}

	@Override
	public Optional<PatientInfo> getInfoById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public PatientInfo getPatientInfoByUserId(long userId) {
		// TODO Auto-generated method stub
		return repo.findByUserId(userId);
	}

	@Override
	public List<PatientInfo> getAllPatientInfo() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public PatientInfo updatePatientInfo(PatientInfo patientInfo, long id) {
		Optional<PatientInfo> updatePatientInfo = repo.findById(id);
		if(updatePatientInfo.isEmpty()) {
			return null;
		}
		updatePatientInfo.get().setAddress(patientInfo.getAddress());
		updatePatientInfo.get().setCity(patientInfo.getCity());
		updatePatientInfo.get().setGender(patientInfo.getGender());
		updatePatientInfo.get().setName(patientInfo.getName());
		updatePatientInfo.get().setPhoneNo(patientInfo.getPhoneNo());
		return repo.save(updatePatientInfo.get());
	}

	@Override
	public String deletePatientInfo(long id) {
		repo.deleteById(id);
		return "Patient Info Deleted Sucessfully";
	}

}
