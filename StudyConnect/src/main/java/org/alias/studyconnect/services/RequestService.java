package org.alias.studyconnect.services;

import java.util.Set;

import javax.persistence.EntityManager;

import org.alias.studyconnect.model.Module;
import org.alias.studyconnect.model.Request;
import org.alias.studyconnect.model.Subject;
import org.alias.studyconnect.model.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class RequestService {
	
	private ObjectMapper objectMapper;
	private EntityManager em;

	public String getReceivedRequest(int userId) throws JsonProcessingException {
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		UserDetails user = em.find(UserDetails.class, userId);
		Set<Request> requests = user.getReqReceived();
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String result = objectMapper.writeValueAsString(requests);
		em.getTransaction().commit();
		em.close();
		return result;	
	}
	
	
	public String getSentRequest(int userId) throws JsonProcessingException {
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		UserDetails user = em.find(UserDetails.class, userId);
		Set<Request> requests = user.getReqSent();
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String result = objectMapper.writeValueAsString(requests);
		em.getTransaction().commit();
		em.close();
		return result;	
	}


	public int addRequest(Request request) {
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		doOperations(request);
		em.persist(request);
		em.getTransaction().commit();
		em.close();	
		return 1;
	}
	
	private void doOperations(Request request){
		UserDetails toUser = em.find(UserDetails.class, request.getRequestId().getToUserId());
		UserDetails fromUser = em.find(UserDetails.class, request.getRequestId().getFromUserId());
		Subject subject = em.find(Subject.class, request.getRequestId().getSubjectCRN());
		request.setUserSent(fromUser);
		request.setUserReceived(toUser);
		request.setSubject(subject);
//		toUser.getReqReceived().add(request);
//		fromUser.getReqSent().add(request);
//		subject.getRequestList().add(request);
	}

}
