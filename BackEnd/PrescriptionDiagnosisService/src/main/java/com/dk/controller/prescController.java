package com.dk.controller;

import java.util.List;
//import java.util.Map;

import com.dk.model.Prescription;
import com.dk.service.prescService;
//import com.dk.service.prescServiceImple;

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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/prescription")
public class prescController {

	@Autowired
	private prescService service;
	
	@PostMapping("/")
	public ResponseEntity<Object> addPrescription(@RequestBody Prescription prescription){
		Prescription res = service.addPrescription(prescription);
		if(res == null) {
			return new ResponseEntity<Object>("Prescription not added", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object> getAllPrescriptions(){
		List<Prescription> prescription = this.service.getPrescription();
		return new ResponseEntity<Object>(prescription, HttpStatus.OK);
	}
	
	@GetMapping("/{prescId}")
	public ResponseEntity<Object> getPrescriptionById(@PathVariable int prescId){
		Prescription res = this.service.getPrescriptionById(prescId).get();
		if(res == null) {
			return new ResponseEntity<Object>("Prescription not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@GetMapping("/patient-id/{patientId}")
	public ResponseEntity<Object> getPrescriptionByPatientId(@PathVariable int patientId){
		Prescription res = this.service.getPrescriptionByPatientId(patientId);
		if(res == null) {
			return new ResponseEntity<Object>("Prescription not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{prescId}")
	public ResponseEntity<Object> deletePrescription(@PathVariable int prescId){
		String res = this.service.deletePrescription(prescId);
		if(res == null) {
			return new ResponseEntity<Object>("Prescription not deleted !", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@PutMapping("/{patientId}")
	public ResponseEntity<Object> updatePrescription(@RequestBody Prescription prescription,@PathVariable int patientId){
		Prescription res = this.service.updatePrescription(prescription, patientId);
		if(res == null) {
			return new ResponseEntity<Object>("Prescription not updated", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>("Prescription updated", HttpStatus.OK);
	}

}
