package com.dk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diagnosis_details")
public class Diagnosis {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String diagnosisTitle;
	
	@Column(nullable = false)
	private String expertComments;

	public Diagnosis() {
		super();
		//TODO Auto-generated constructor stub
	}

	public Diagnosis(long id, String diagnosisTitle, String expertComments) {
		super();
		this.id = id;
		this.diagnosisTitle = diagnosisTitle;
		this.expertComments = expertComments;
	}

	public long getid() {
		return id;
	}

	public void setid(long id) {
		this.id = id;
	}

	public String getDiagnosisTitle() {
		return diagnosisTitle;
	}

	public void setDiagnosisTitle(String diagnosisTitle) {
		this.diagnosisTitle = diagnosisTitle;
	}

	public String getExpertComments() {
		return this.expertComments;
	}

	public void setExpertComments(String expertComments) {
		this.expertComments = expertComments;
	}
	
}
