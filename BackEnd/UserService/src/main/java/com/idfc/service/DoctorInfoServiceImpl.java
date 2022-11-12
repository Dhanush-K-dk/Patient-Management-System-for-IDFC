package com.idfc.service;

import java.util.List;
import java.util.Optional;

import com.idfc.dao.DoctorInfoRepository;
import com.idfc.model.DoctorInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DoctorInfoServiceImpl implements DoctorInfoService {

	@Autowired
	private DoctorInfoRepository repo;
	
	@Override
	public DoctorInfo addDoctorInfo(DoctorInfo doctorInfo) {
		// TODO Auto-generated method stub
		return repo.save(doctorInfo);
	}

	@Override
	public Optional<DoctorInfo> getInfoById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public DoctorInfo getDoctorInfoByUserId(long userId) {
		// TODO Auto-generated method stub
		return repo.findByUserId(userId);
	}

	@Override
	public List<DoctorInfo> getAllDoctorInfo(String specialization) {
		// TODO Auto-generated method stub
		if(specialization.equals("all")) {
			return repo.findAll();
		}
		return repo.findBySpecialization(specialization);
	}

	@Override
	public DoctorInfo updateDoctorInfo(DoctorInfo doctorInfo, long id) {
		Optional<DoctorInfo> updateDocInfo = repo.findById(id);
		if(updateDocInfo.isEmpty()) {
			return null;
		}
		updateDocInfo.get().setEducation(doctorInfo.getEducation());
		updateDocInfo.get().setName(doctorInfo.getName());
		updateDocInfo.get().setPhoneNo(doctorInfo.getPhoneNo());
		updateDocInfo.get().setSpecialization(doctorInfo.getSpecialization());
		return repo.save(updateDocInfo.get());
	}

	@Override
	public String deleteDoctorInfo(long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return "Doctor Info deleted sucessfully";
	}

}
