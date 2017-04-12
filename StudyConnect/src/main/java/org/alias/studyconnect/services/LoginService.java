package org.alias.studyconnect.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import org.alias.studyconnect.model.Subject;
import org.alias.studyconnect.model.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javassist.NotFoundException;

public class LoginService {

	private EntityManager em;
	private ObjectMapper objectMapper;

	public String login(int id, String password) {
		// TODO Auto-generated method stub
		
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		try {
			validate(id, password);
		} catch (NotFoundException e) {
			return null;
		}

		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String result;
		EntityGraph<?> graph = this.em.getEntityGraph("graph.User.subjects");
		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put("javax.persistence.fetchgraph", graph);
		UserDetails user = this.em.find(UserDetails.class, id ,hints);
		try {
			result = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		em.getTransaction().commit();
		em.close();

		return result;
	}

	private void validate(int id, String password) throws NotFoundException {
		UserDetails user = (UserDetails)em.find(UserDetails.class, id);
		if (!user.getPassword().equals(password))
			throw new NotFoundException("Not Found");
	}

}
