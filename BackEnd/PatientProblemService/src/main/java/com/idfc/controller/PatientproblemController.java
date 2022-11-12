package com.idfc.controller;

import java.util.List;

import com.idfc.model.PatientProblem;
import com.idfc.service.PatientProblemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patientProblem")
public class PatientproblemController {

	@Autowired
	private PatientProblemService service;
	
	@PostMapping("/")
	public ResponseEntity<Object> addPatientProblem(@RequestBody PatientProblem patientProblem){
		PatientProblem resProblem = this.service.addPatientProblem(patientProblem);
		if(resProblem == null) {
			return new ResponseEntity<Object>("Patient Problem not added Sucessfully", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(resProblem, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object> getAllPatientProblems(){
		List<PatientProblem> res = this.service.getPatientProblem();
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{patientId}")
	public ResponseEntity<Object> getPatientProblemByPatientId(@PathVariable long patientId){
		List<PatientProblem> resUser = this.service.getPatientProblemByPatientId(patientId);
		if(resUser.size() == 0) {
			return new ResponseEntity<Object>("patient problem not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(resUser, HttpStatus.OK);
	}
	
	@PatchMapping("/{patientId}")
	public ResponseEntity<Object> updatePatientProblem(@RequestBody PatientProblem patientProblem, @PathVariable long patientId){
		PatientProblem resUser = this.service.updatePatientProblem(patientProblem, patientId);
		
		if(resUser == null) {
			return new ResponseEntity<Object>("patient problem updation failed", HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<Object>("patient problem updated sucessfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{patientId}")
	public ResponseEntity<Object> removePatientProblem(@PathVariable long patientId){
		String res = this.service.deletePatientProblem(patientId);
		if(res == null) {
			return new ResponseEntity<Object>("patient problem not deleted !", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getPatientByDoctorId")
	public ResponseEntity<Object> getPatientProblemByDoctorId(@RequestParam long doctorId){
		List<PatientProblem> res = service.getPatientProblemByDoctorId(doctorId);
		if(res.size() == 0) {
			return new ResponseEntity<Object>("patient problem not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getProblemByProblemId/{problemId}")
	public ResponseEntity<Object> getPatientProbjemByProblemId(@PathVariable long problemId){
		PatientProblem res = service.getPatientProblemById(problemId).get();
		if(res == null) {
			return new ResponseEntity<Object>("patient problem not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
}
