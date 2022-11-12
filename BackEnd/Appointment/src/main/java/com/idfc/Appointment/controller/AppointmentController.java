package com.idfc.Appointment.controller;

import java.util.List;
//import java.util.Map;

import com.idfc.Appointment.model.Appointment;
import com.idfc.Appointment.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService service;

	@PostMapping("/")
	public ResponseEntity<Object> addAppointment(@RequestBody Appointment appointment) {
		Appointment resUser = this.service.addAppointment(appointment);
		if (resUser == null) {
			return new ResponseEntity<Object>("Appointment not add Sucessfully", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(resUser, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<Object> getAllAppointments() {
		List<Appointment> appointments = this.service.getAppointment();
		return new ResponseEntity<Object>(appointments, HttpStatus.OK);
	}
	
	@GetMapping("/appiontmentId{appointmentId}")
	public ResponseEntity<Object> getAppointmentById(@PathVariable int appointmentId) {
		Appointment resUser = this.service.getAppointmentById(appointmentId);
		if (resUser == null) {
			return new ResponseEntity<Object>("Appointment not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(resUser, HttpStatus.OK);
	}

	@GetMapping("/{patientId}")
	public ResponseEntity<Object> getAppointmentByPatientId(@PathVariable int patientId) {
		Appointment resUser = this.service.getAppointmentByPatientId(patientId);
		if (resUser == null) {
			return new ResponseEntity<Object>("Appointment not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(resUser, HttpStatus.OK);
	}
	
	@GetMapping("/getAppointmentByDoctorEmail/{doctorId}")
	public ResponseEntity<Object> getAppointmentByDoctorEmail(@RequestParam int doctorId){
		List<Appointment> res = this.service.getAppointmentByDoctorId(doctorId);
		if(res == null) {
			return new ResponseEntity<Object>("Appointment not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	@PutMapping("/updateAppointmentDate")
	public ResponseEntity<Object> updateAppointmentDate(@RequestParam int appointmentId, @RequestParam String appointmentDate) {
		Appointment resUser = this.service.updateAppointmentDate(appointmentId, appointmentDate);

		if (resUser == null) {
			return new ResponseEntity<Object>("AppointmentDate updation failed", HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Object>("AppointmentDate updated sucessfully", HttpStatus.OK);
	}
	
	@PutMapping("/updateAppointmentTime")
	public ResponseEntity<Object> updateAppointmentTime(@RequestParam int appointmentId, @RequestParam String appointmentTime) {
		Appointment resUser = this.service.updateAppointmentTime(appointmentId, appointmentTime);

		if (resUser == null) {
			return new ResponseEntity<Object>("AppointmentTime updation failed", HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Object>("AppointmentTime updated sucessfully", HttpStatus.OK);
	}

	@PutMapping("/{appointmentId}")
	public ResponseEntity<Object> udateAppointment(@RequestBody Appointment appointment,@PathVariable int appointmentId) {
		Appointment resUser = this.service.updateAppointment(appointment, appointmentId);
		if (resUser == null) {
			return new ResponseEntity<Object>("Appointment not added Sucessfully", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>("Appointment updated", HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{appointmentId}")
	public ResponseEntity<Object> deleteAppointment(@PathVariable int appointmentId) {
		String res = this.service.deleteAppointment(appointmentId);
		if (res == null) {
			return new ResponseEntity<Object>("Appointment not deleted !", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	@GetMapping("/checkAppointmentBooking")
	public ResponseEntity<Object> checkAppointmentBooking(@RequestParam String date, @RequestParam int doctorId){
		Object res = service.checkAppointmentAvailable(date, doctorId);
		if(res.toString().equals("false")) {
			return new ResponseEntity<Object>((Boolean) res, HttpStatus.NOT_ACCEPTABLE);
		}
		if(res.toString().equals("true")) {
			return new ResponseEntity<Object>((Boolean) res, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(res, HttpStatus.ACCEPTED);
	}
}
