package com.idfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor_personal_info")
public class DoctorInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String Education;
	

	@Column(nullable = false)
	private String specialization;
	
	@Column(nullable = false)
	private long phoneNo;
	
	@Column(nullable = false)
	private Long userId;
	
	public DoctorInfo() {
		super();
		//TODO Auto-generated constructor stub
	}

	public DoctorInfo(long id, String name, String education, String specialization, long phoneNo, Long userId) {
		super();
		this.id = id;
		this.name = name;
		Education = education;
		this.specialization = specialization;
		this.phoneNo = phoneNo;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEducation() {
		return Education;
	}

	public void setEducation(String education) {
		Education = education;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
