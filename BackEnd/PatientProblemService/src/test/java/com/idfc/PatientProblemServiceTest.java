package com.idfc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.willDoNothing;
//import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.idfc.dao.PatientProblemRepository;
import com.idfc.model.PatientProblem;
import com.idfc.service.PatientProblemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PatientProblemServiceTest {

	@MockBean
	private PatientProblemRepository repo;
	
	@Autowired
	private PatientProblemService underTest;
	
	PatientProblem patientProblem;
	
	@BeforeEach
	void setUp() throws Exception {
		patientProblem = new PatientProblem(1, 10, "some symptoms", "some past medic", 3);
	}
//
//	@Test
//	void testAddPatientProblem() {
////		When
//		underTest.addPatientProblem(patientProblem);
//		
////		Then
//		ArgumentCaptor<PatientProblem> argCapture = ArgumentCaptor.forClass(PatientProblem.class);
////		capturing the values from the save method to test
//		verify(repo).save(argCapture.capture());
//		PatientProblem capturedUser = argCapture.getValue();
//		assertThat(capturedUser).isEqualTo(patientProblem);
//	}
//
//	@Test
//	void testGetPatientProblemById() {
////		Given
//		given(repo.findById((long) 11)).willReturn(Optional.of(patientProblem));
////		When
//		Optional<PatientProblem> res = underTest.getPatientProblemById(patientProblem.getProblemId());
////		System.out.println(res);
////		Then
//		assertThat(res).isNotNull();
//	}
//
//	@Test
//	void testGetPatientProblemByPatientId() {
////		Given
//		given(repo.findByPatientId(50)).willReturn(patientProblem);
////		When
//		PatientProblem res = underTest.getPatientProblemByPatientId(patientProblem.getPatientId());
////		System.out.println(res);
////		Then
//		assertThat(res).isNotNull();
//	}
//
//	@Test
//	void testGetPatientProblem() {
////		When
//		underTest.getPatientProblem();
////		Then
//		verify(repo).findAll();
//	}
//
//	@Test
//	void testUpdatePatientProblem() {
////		Given
//		PatientProblem updatePatientProblem = new PatientProblem(11, 50, "Headache reduced", "Good Health");
//		given(repo.findByPatientId(50)).willReturn(patientProblem);
////		When
//		underTest.updatePatientProblem(updatePatientProblem, 50);
////		Then
//		ArgumentCaptor<PatientProblem> argCapture = ArgumentCaptor.forClass(PatientProblem.class);
//		verify(repo).save(argCapture.capture());
//		PatientProblem capturedUser = argCapture.getValue();
//		assertThat(capturedUser.getSympoms()).isEqualTo("Headache reduced");
//	}
///*
////		To be Checked
//	@Test
//	void testDeletePatientProblem() {
////		Given
//		willDoNothing().given(repo).deleteById((long) 11);
//		given(repo.findByPatientId(50)).willReturn(patientProblem);
////		When
//		underTest.deletePatientProblem(50);
////		Then
//		verify(repo, times(1)).deleteById((long) 11);
//	}
*/
}
