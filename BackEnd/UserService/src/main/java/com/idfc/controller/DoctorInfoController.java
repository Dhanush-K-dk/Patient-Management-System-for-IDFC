package com.idfc.controller;

import java.util.List;

import com.idfc.model.DoctorInfo;
import com.idfc.service.DoctorInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/doctorInfo")
public class DoctorInfoController {

	@Autowired
	private DoctorInfoService service;
	
	@PostMapping("/")
	public ResponseEntity<Object> addDoctorInfo(@RequestBody DoctorInfo doctorInfo){
		DoctorInfo res = this.service.addDoctorInfo(doctorInfo);
		if(res == null) {
			return new ResponseEntity<Object>("Doctor Information not add Sucessfully", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(res, HttpStatus.CREATED);
	}

	@GetMapping("/specialization")
	public ResponseEntity<Object> getAllDoctorInfo(@RequestParam String specialization){
		List<DoctorInfo> res = this.service.getAllDoctorInfo(specialization);
		if(res.size() == 0) {
			return new ResponseEntity<Object>("Result not found!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	@GetMapping("/{doctorId}")
	public ResponseEntity<Object> getDoctorInfoById(@PathVariable long doctorId){
		DoctorInfo res = this.service.getInfoById(doctorId).get();
		if(res == null) {
			return new ResponseEntity<Object>("Doctor info not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Object> getDoctorInfoByUserId(@PathVariable long userId){
		DoctorInfo res = this.service.getDoctorInfoByUserId(userId);
		if(res == null) {
			return new ResponseEntity<Object>("Doctor info not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	@PutMapping("/{doctorId}")
	public ResponseEntity<Object> updatePatientInfo(@RequestBody DoctorInfo doctorInfo,@PathVariable long doctorId){
		DoctorInfo res = service.updateDoctorInfo(doctorInfo, doctorId);
		if(res == null) {
			return new ResponseEntity<Object>("Patient Info not update", HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{doctorId}")
	public ResponseEntity<Object> removeUser(@PathVariable long doctorId){
		String res = this.service.deleteDoctorInfo(doctorId);
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
}
