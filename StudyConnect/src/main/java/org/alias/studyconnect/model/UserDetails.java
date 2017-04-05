package org.alias.studyconnect.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@XmlRootElement	
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY )
public class UserDetails {
	
	@Id
	private int userId;
	@Column(nullable = false)
	private String userName;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUBJECT_ID")
	private Set<Subject> subjectList = new HashSet<>();
	@ManyToMany(mappedBy = "user")
	private Set<Module> moduleCompleted = new HashSet<>();
	@OneToMany(mappedBy ="userSent")
	Set<Request> reqSent = new HashSet<>();
	@OneToMany(mappedBy = "userReceived")
	Set<Request> reqReceived = new HashSet<>();
	//@Embedded
	//private ChatDetails chatDetails = new ChatDetails();

	public UserDetails(){}
	
	//Getters and Setters
	
	//UserID
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	//UserName
	public String getUserName() {
		return userName;
	}

	public void setUserName(@NotNull String userName) {
		this.userName = userName;
	}
	
	//Email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Password
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//ChatDetails
//	public ChatDetails getChatDetails() {
//		return chatDetails;
//	}
//
//	public void setChatDetails(ChatDetails chatDetails) {
//		this.chatDetails = chatDetails;
//	}
	
//	SubjectList
	public Set<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(Set<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public Set<Request> getReqSent() {
		return reqSent;
	}

	public void setReqSent(Set<Request> reqSent) {
		this.reqSent = reqSent;
	}

	public Set<Request> getReqReceived() {
		return reqReceived;
	}

	public void setReqReceived(Set<Request> reqReceived) {
		this.reqReceived = reqReceived;
	}
	
	public Set<Module> getModuleCompleted() {
		return moduleCompleted;
	}

	public void setModuleCompleted(Set<Module> moduleCompleted) {
		this.moduleCompleted = moduleCompleted;
	}
	
	
}
