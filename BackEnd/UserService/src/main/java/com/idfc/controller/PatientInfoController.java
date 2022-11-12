package com.idfc.controller;

import java.util.List;

import com.idfc.model.PatientInfo;
import com.idfc.service.PatientInfoService;

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
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patientInfo")
public class PatientInfoController {

	@Autowired
	private PatientInfoService service;

	@PostMapping("/")
	public ResponseEntity<Object> addPatientInfo(@RequestBody PatientInfo patientInfo){
		PatientInfo res = this.service.addPatientInfo(patientInfo);
		if(res == null) {
			return new ResponseEntity<Object>("Patient Information not add Sucessfully", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(res, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<Object> getAllPatientInfo(){
		List<PatientInfo> res = this.service.getAllPatientInfo();
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	@GetMapping("/{patientId}")
	public ResponseEntity<Object> getPatientInfoById(@PathVariable long patientId){
		PatientInfo res = this.service.getInfoById(patientId).get();
		if(res == null) {
			return new ResponseEntity<Object>("Patient not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Object> getPatientInfoByUserId(@PathVariable long userId){
		PatientInfo res = this.service.getPatientInfoByUserId(userId);
		if(res == null) {
			return new ResponseEntity<Object>("Patient not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	@PutMapping("/{patientId}")
	public ResponseEntity<Object> updatePatientInfo(@RequestBody PatientInfo patientInfo,@PathVariable long patientId){
		PatientInfo res = service.updatePatientInfo(patientInfo, patientId);
		if(res == null) {
			return new ResponseEntity<Object>("Patient Info not update", HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{patientId}")
	public ResponseEntity<Object> removeUser(@PathVariable long patientId){
		String res = this.service.deletePatientInfo(patientId);
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
}
