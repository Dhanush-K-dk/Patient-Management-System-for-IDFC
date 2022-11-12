package com.dk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.dk.dao.prescRepository;
import com.dk.model.Prescription;
import com.dk.model.Diagnosis;
import com.dk.service.prescService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class prescServiceTest {

	@MockBean
	private prescRepository repo;
	
	@Autowired
	private prescService underTest;
	
	Prescription prescription;
	Diagnosis diagnosis;
	
	@BeforeEach
	void setUp() throws Exception {
		diagnosis = new Diagnosis(1l, "Cold and Fever",	"Take rest and drink water");
		prescription = new Prescription(1, 100, "Medicines", diagnosis);
	}

	@Test
	void testAddPrescription() {
//		When
		underTest.addPrescription(prescription);
		
//		Then
		ArgumentCaptor<Prescription> argCapture = ArgumentCaptor.forClass(Prescription.class);
//		capturing the values from the save method to test
		verify(repo).save(argCapture.capture());
		Prescription capturedUser = argCapture.getValue();
		assertThat(capturedUser).isEqualTo(prescription);
	}

	@Test
	void testGetPrescriptionById() {
//		Given
		given(repo.findById(1)).willReturn(Optional.of(prescription));
//		When
		Prescription res = underTest.getPrescriptionById(prescription.getPrescId()).get();
//		System.out.println(res);
//		Then
		assertThat(res).isNotNull();
	}

	@Test
	void testGetPrescriptionByPatientId() {
//		Given
		given(repo.findByPatientId(100)).willReturn(prescription);
//		When
		Prescription res = underTest.getPrescriptionByPatientId(prescription.getPatientId());
//		System.out.println(res);
//		Then
		assertThat(res).isNotNull();
	}

	@Test
	void testGetPrescription() {
//		When
		underTest.getPrescription();
//		Then
		verify(repo).findAll();
	}

	@Test
	void testUpdatePrescription() {
//		Given
		Prescription updatePrescription = new Prescription(1,100,"Drugs", diagnosis);
		given(repo.findByPatientId(100)).willReturn(prescription);
//		When
		underTest.updatePrescription(updatePrescription, 100);
//		Then
		ArgumentCaptor<Prescription> argCapture = ArgumentCaptor.forClass(Prescription.class);
		verify(repo).save(argCapture.capture());
		Prescription capturedUser = argCapture.getValue();
		assertThat(capturedUser.getPrescDetails()).isEqualTo("Drugs");
	}

	@Test
	void testDeletePrescription() {
//		Given
		willDoNothing().given(repo).deleteById(1);
		given(repo.findById(1)).willReturn(Optional.of(prescription));
//		When
		underTest.deletePrescription(1);
//		Then
		verify(repo, times(1)).deleteById(1);
	}

}
