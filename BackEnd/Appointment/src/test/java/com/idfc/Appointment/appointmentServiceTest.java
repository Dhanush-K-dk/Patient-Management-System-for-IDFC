package com.idfc.Appointment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.idfc.Appointment.dao.AppointmentRepository;
import com.idfc.Appointment.model.Appointment;
import com.idfc.Appointment.service.AppointmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class appointmentServiceTest {

	@MockBean
	private AppointmentRepository repo;
	
	@Autowired
	private AppointmentService underTest;
	
	Appointment appointment;
	
//	@BeforeEach
//	void setUp() throws Exception {
//		appointment = new Appointment(1,50,"dk@gmail.com","10/11/22","5:00pm");
//	}
//
//	@Test
//	void testAddAppointment() {
////		When
//		underTest.addAppointment(appointment);
//		
////		Then
//		ArgumentCaptor<Appointment> argCapture = ArgumentCaptor.forClass(Appointment.class);
////		capturing the values from the save method to test
//		verify(repo).save(argCapture.capture());
//		Appointment capturedUser = argCapture.getValue();
//		assertThat(capturedUser).isEqualTo(appointment);
//	}
//	
//	@Test
//	void testDeleteAppointment() {
////		Given
//		willDoNothing().given(repo).deleteById(1);
//		given(repo.findById(1)).willReturn(Optional.of(appointment));
////		When
//		underTest.deleteAppointment(1);
////		Then
//		verify(repo, times(1)).deleteById(1);
//	}
//	
//	@Test
//	void testGetAllAppointments() {
////		When
//		underTest.getAppointment();
////		Then
//		verify(repo).findAll();
//	}
//	
//	@Test
//	void testUpdateAppointment() {
////		Given
//		Appointment updateAppointment = new Appointment(1,50,"dk@gmail.com","11/11/22","6:00pm");
//		given(repo.findById(1)).willReturn(Optional.of(appointment));
////		When
//		underTest.updateAppointment(updateAppointment, 1);
////		Then
//		ArgumentCaptor<Appointment> argCapture = ArgumentCaptor.forClass(Appointment.class);
//		verify(repo).save(argCapture.capture());
//		Appointment capturedUser = argCapture.getValue();
//		assertThat(capturedUser.getAppointmentDate()).isEqualTo("11/11/22");
//	}
//	
//	//to check
//	@Test
//	void testGetAppointmentById() {
////		Given
//		given(repo.findById(1)).willReturn(Optional.of(appointment));
////		When
//		Appointment res = underTest.getAppointmentById(appointment.getAppointmentId());
////		System.out.println(res);
////		Then
//		assertThat(res).isNotNull();
//	}
//	
//	@Test
//	void testUpdateAppointmentDate() {
////		Given
//		given(repo.findById(1)).willReturn(Optional.of(appointment));
////		When
//		underTest.updateAppointmentDate(1, "11/11/22");
////		Then
//		ArgumentCaptor<Appointment> argCapture = ArgumentCaptor.forClass(Appointment.class);
//		verify(repo).save(argCapture.capture());
//		Appointment capturedUser = argCapture.getValue();
//		assertThat(capturedUser.getAppointmentDate()).isEqualTo("11/11/22");
//	}
//	
//	@Test
//	void testUpdateAppointmentTime() {
////		Given
//		given(repo.findById(1)).willReturn(Optional.of(appointment));
////		When
//		underTest.updateAppointmentTime(1, "2pm");
////		Then
//		ArgumentCaptor<Appointment> argCapture = ArgumentCaptor.forClass(Appointment.class);
//		verify(repo).save(argCapture.capture());
//		Appointment capturedUser = argCapture.getValue();
//		assertThat(capturedUser.getAppointmentTime()).isEqualTo("2pm");
//	}
//	
//	@Test
//	void testGetAppointmentByDoctorEmail() {
////		Given
//		given(repo.findByDoctorEmail("dk@gmail.com")).willReturn(appointment);
////		When
//		Appointment res = underTest.getAppointmentByDoctorEmail(appointment.getDoctorEmail());
////		System.out.println(res);
////		Then
//		assertThat(res).isNotNull();
//	}
//	
//	@Test
//	void testGetAppointmentByPatientId() {
////		Given
//		given(repo.findByPatientId(50)).willReturn(appointment);
////		When
//		Appointment res = underTest.getAppointmentByPatientId(appointment.getPatientId());
////		System.out.println(res);
////		Then
//		assertThat(res).isNotNull();
//	}

}
