package org.alias.studyconnect.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deptId;
	private String name;
	@ManyToOne
	@JoinColumn(name = "COLLEGE_ID")
	@JsonIgnore
	private College collegeId;
	@OneToMany(mappedBy = "dept", fetch = FetchType.EAGER) 
	@JsonIgnore
	private List<Subject> subjectList = new ArrayList<>();
	
	public Department(){}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public College getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(College collegeId) {
		this.collegeId = collegeId;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	
	
}
