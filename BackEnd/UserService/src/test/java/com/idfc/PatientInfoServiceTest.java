package com.idfc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.idfc.dao.PatientInfoRepository;
import com.idfc.model.PatientInfo;
import com.idfc.service.PatientInfoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PatientInfoServiceTest {

	@MockBean
	private PatientInfoRepository repo;
	
	@Autowired
	private PatientInfoService underTest;
	
	PatientInfo patientInfo;
	
	@BeforeEach
	void setUp() throws Exception {
		patientInfo = new PatientInfo(
				1l, 
				"patient 1", 
				"male", 
				"house no21 malad", 
				"mumbai", 
				9835437653l, 
				56l);
	}

	@Test
	void testAddPatientInfo() {
//		When
		underTest.addPatientInfo(patientInfo);
		
//		Then
		ArgumentCaptor<PatientInfo> argCapture = ArgumentCaptor.forClass(PatientInfo.class);
//		capturing the values from the save method to test
		verify(repo).save(argCapture.capture());
		PatientInfo capturedUser = argCapture.getValue();
		assertThat(capturedUser).isEqualTo(patientInfo);
	}

	@Test
	void testGetInfoById() {
//		Given
		given(repo.findById(1l)).willReturn(Optional.of(patientInfo));
//		When
		PatientInfo res = underTest.getInfoById(patientInfo.getId()).get();
//		System.out.println(res);
//		Then
		assertThat(res).isNotNull();
	}

	@Test
	void testGetPatientInfoByUserId() {
//		Given
		given(repo.findByUserId(56l)).willReturn(patientInfo);
//		When
		PatientInfo res = underTest.getPatientInfoByUserId(patientInfo.getUserId());
//		System.out.println(res);
//		Then
		assertThat(res).isNotNull();
	}

	@Test
	void testGetAllPatientInfo() {
//		When
		underTest.getAllPatientInfo();
//		Then
		verify(repo).findAll();
	}

	@Test
	void testUpdatePatientInfo() {
//		Given
		PatientInfo updatePatientInfo = new PatientInfo(
				1l, 
				"patient 1", 
				"male", 
				"house no21 malad", 
				"chennai", 
				9835437653l, 
				56l);
		given(repo.findById(1l)).willReturn(Optional.of(patientInfo));
//		When
		underTest.updatePatientInfo(updatePatientInfo, 1l);
//		Then
		ArgumentCaptor<PatientInfo> argCapture = ArgumentCaptor.forClass(PatientInfo.class);
		verify(repo).save(argCapture.capture());
		PatientInfo capturedUser = argCapture.getValue();
		assertThat(capturedUser.getCity()).isEqualTo("chennai");
	}

	@Test
	void testDeletePatientInfo() {
//		Given
		willDoNothing().given(repo).deleteById(1l);
		given(repo.findById(1l)).willReturn(Optional.of(patientInfo));
//		When
		underTest.deletePatientInfo(1l);
//		Then
		verify(repo, times(1)).deleteById(1l);
	}

}
