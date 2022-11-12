package com.dk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.dk.dao.diagRepository;
import com.dk.model.Diagnosis;
import com.dk.service.diagService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class diagServiceTest {

	@MockBean
	private diagRepository repo;
	
	@Autowired
	private diagService underTest;
	
	Diagnosis diagnosis;
	
	@BeforeEach
	void setUp() throws Exception {
		diagnosis = new Diagnosis(
				1l, 
				"Cold and Fever", 
				"Take rest and drink water");
	}

	@Test
	void testAddDiagnosis() {
//		When
		underTest.addDiagnosis(diagnosis);
		
//		Then
		ArgumentCaptor<Diagnosis> argCapture = ArgumentCaptor.forClass(Diagnosis.class);
//		capturing the values from the save method to test
		verify(repo).save(argCapture.capture());
		Diagnosis capturedUser = argCapture.getValue();
		assertThat(capturedUser).isEqualTo(diagnosis);
	}
	
	@Test
	void testDeleteDiagnosis() {
//		Given
		willDoNothing().given(repo).deleteById(1l);
		given(repo.findById(1l)).willReturn(Optional.of(diagnosis));
//		When
		underTest.deleteDiagnosis(1l);
//		Then
		verify(repo, times(1)).deleteById(1l);
	}
	
	@Test
	void testGetAllDiagnosis() {
//		When
		underTest.getDiagnosis();
//		Then
		verify(repo).findAll();
	}
	
	@Test
	void testUpdateDiagnosis() {
//		Given
		Diagnosis updateDiagnosis = new Diagnosis(
				1l, 
				"Cold and Fever", 
				"Take rest, drink water and take dolo 650");
		given(repo.findById(1l)).willReturn(Optional.of(diagnosis));
//		When
		underTest.updateDiagnosis(updateDiagnosis, 1l);
//		Then
		ArgumentCaptor<Diagnosis> argCapture = ArgumentCaptor.forClass(Diagnosis.class);
		verify(repo).save(argCapture.capture());
		Diagnosis capturedUser = argCapture.getValue();
		assertThat(capturedUser.getExpertComments()).isEqualTo("Take rest, drink water and take dolo 650");
	}
	
	//to check
	@Test
	void testGetDiagnosisById() {
//		Given
		given(repo.findById(1l)).willReturn(Optional.of(diagnosis));
//		When
		Diagnosis res = underTest.getDiagnosisById(diagnosis.getid()).get();
//		System.out.println(res);
//		Then
		assertThat(res).isNotNull();
	}

}
