package org.alias.studyconnect.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Module implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int moduleId;
	private String moduleName;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "SUBJECT_CRN")
	private Subject subjectId; // subjectID
	@ManyToMany
	Set<UserDetails> user = new HashSet<>();
	
	public Module(){
		
	}
	
	
	public Subject getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Subject subjectId) {
		this.subjectId = subjectId;
	}
	public Set<UserDetails> getUser() {
		return user;
	}
	
	public void setUser(Set<UserDetails> user) {
		this.user = user;
	}
	//Getters and Setters
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
}
