package org.alias.studyconnect.services;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.alias.studyconnect.model.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class RegistrationService {
	private EntityManager entityManager;
	private ObjectMapper objectMapper;

	public String registerUser(UserDetails user) {
		entityManager = EntityUtil.getEntityManager();
		String result = "";
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			result = objectMapper.writeValueAsString(user);
			entityManager.close();
		} catch (RollbackException e) {
			return "rollback";
		} catch (JsonProcessingException e) {
			return "json";
		}
		return result;
	}

	public String updateUserInfo(UserDetails user) {
		entityManager = EntityUtil.getEntityManager();
		String result = "";
		try {
			entityManager.getTransaction().begin();
			UserDetails tempUser = entityManager.find(UserDetails.class, user.getUserId());
			tempUser.setUserName(user.getUserName());
			tempUser.setPassword(user.getPassword());
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (RollbackException e) {
			return "rollback";
		}
		return result;
	}

}
