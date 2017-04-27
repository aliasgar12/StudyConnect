package org.alias.studyconnect.services;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.alias.studyconnect.model.Module;
import org.alias.studyconnect.model.Request;
import org.alias.studyconnect.model.RequestId;
import org.alias.studyconnect.model.Subject;
import org.alias.studyconnect.model.UserDetails;
import org.alias.studyconnect.notification.NotificationServer;
import org.hibernate.TypeMismatchException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import antlr.MismatchedTokenException;
import javassist.NotFoundException;

public class RequestService {

	private ObjectMapper objectMapper;
	private EntityManager em;
	private final int SEND_REQUEST = 0;
	private final int ACCEPT_REQUEST = 1;

	public String getReceivedRequest(int userId) throws JsonProcessingException {
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		UserDetails user = em.find(UserDetails.class, userId);
		Set<Request> requests = new HashSet<>();
		for (Request req : user.getReqReceived())
			if (req.getFlag() == 0)
				requests.add(req);
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
		try {
			em = EntityUtil.getEntityManager();
			em.getTransaction().begin();
			doOperations(request);
			em.getTransaction().commit();
			em.close();
		} catch (RollbackException e) {
			e.printStackTrace();
			return 0;
		} catch (NotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return 0;
		} catch (EntityExistsException e) {
			e.printStackTrace();
			return 409;
		}
		return 1;
	}

	private void doOperations(Request request)
			throws NotFoundException, TypeMismatchException, IllegalArgumentException {
		UserDetails toUser = em.find(UserDetails.class, request.getRequestId().getToUserId());
		UserDetails fromUser = em.find(UserDetails.class, request.getRequestId().getFromUserId());
		Module module = em.find(Module.class, request.getRequestId().getModuleId());
		request.setUserSent(fromUser);
		request.setUserReceived(toUser);
		request.setModule(module);
		toUser.getReqReceived().add(request);
		fromUser.getReqReceived().add(request);
		module.getRequestList().add(request);
		em.persist(request);
		NotificationServer.getInstance(fromUser.getUserName(), module.getModuleName(),
				module.getSubjectId().getSubjectName(), toUser.getApp_token(), SEND_REQUEST).sendRequest();

	}

	public int deleteRequest(Request request) {
		try {
			em = EntityUtil.getEntityManager();
			em.getTransaction().begin();
			RequestId reqid = request.getRequestId();
			Request req = em.find(Request.class, reqid);
			// doDelOperations(request);
			req = em.merge(req);
			em.remove(req);
			em.getTransaction().commit();
			em.close();
		} catch (RollbackException e) {
			return 0;
		}
		return 1;
	}

	// private void doDelOperations(Request request)
	// throws NotFoundException, TypeMismatchException, IllegalArgumentException
	// {
	// UserDetails toUser = em.find(UserDetails.class,
	// request.getRequestId().getToUserId());
	// UserDetails fromUser = em.find(UserDetails.class,
	// request.getRequestId().getFromUserId());
	// Module module = em.find(Module.class,
	// request.getRequestId().getModuleId());
	// toUser.getReqReceived().remove(request);
	// fromUser.getReqSent().remove(request);
	// module.getRequestList().remove(request);
	//
	// }

	public int acceptRequest(Request request) {
		try {
			em = EntityUtil.getEntityManager();
			em.getTransaction().begin();
			// try setting the flag and merging it without searching for it
			RequestId reqid = request.getRequestId();
			Request req = em.find(Request.class, reqid);
			req.setFlag(1);
			sendNotification(req);
			em.getTransaction().commit();
			em.close();
		} catch (RollbackException e) {
			e.printStackTrace();
			return 0;
		} catch (NotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return 0;
		} catch (EntityExistsException e) {
			e.printStackTrace();
			return 409;
		}
		return 1;
	}

	private void sendNotification(Request request)
			throws NotFoundException, TypeMismatchException, IllegalArgumentException {
		UserDetails toUser = em.find(UserDetails.class, request.getRequestId().getToUserId());
		Module module = em.find(Module.class, request.getRequestId().getModuleId());
		em.merge(request);
		NotificationServer.getInstance(request.getToUserName(), module.getModuleName(),
				module.getSubjectId().getSubjectName(), toUser.getApp_token(), ACCEPT_REQUEST).sendRequest();

	}

	public String getAcceptedRequest(int userId) {
		String result;
		try {
			em = EntityUtil.getEntityManager();

			em.getTransaction().begin();
			UserDetails user = em.find(UserDetails.class, userId);
			Set<Request> requests = new HashSet<>();
			for (Request req : user.getReqReceived())
				if (req.getFlag() == 1)
					requests.add(req);
			objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			result = objectMapper.writeValueAsString(requests);
			em.getTransaction().commit();
			em.close();
		} catch (RollbackException e) {
			e.printStackTrace();
			return "";
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
		return result;
	}

}
