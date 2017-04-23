package org.alias.studyconnect.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Request {
	
	@EmbeddedId
	private RequestId requestId;
	@ManyToOne
	@JoinColumn(name = "MODULE_ID")
	@JsonIgnore
	private Module module;
	@ManyToOne
	@JsonIgnore
	private UserDetails userSent;
	@ManyToOne
	@JsonIgnore
	private UserDetails userReceived;
	private int flag;
	private String toUserName;
	private String fromUserName;
	
	
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Request(){}

	//Getters and Setters

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	public RequestId getRequestId() {
		return requestId;
	}

	public void setRequestId(RequestId requestId) {
		this.requestId = requestId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public UserDetails getUserSent() {
		return userSent;
	}

	public void setUserSent(UserDetails userSent) {
		this.userSent = userSent;
	}

	public UserDetails getUserReceived() {
		return userReceived;
	}

	public void setUserReceived(UserDetails userReceived) {
		this.userReceived = userReceived;
	}

}
