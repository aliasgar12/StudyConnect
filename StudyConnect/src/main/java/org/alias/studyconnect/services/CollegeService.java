package org.alias.studyconnect.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.alias.studyconnect.model.College;
import org.alias.studyconnect.model.Subject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CollegeService {

	
	private ObjectMapper objectMapper;
	private EntityManager em;
	
	public String getCollegeList() {
		
		String collegeListJson="";
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("from College");
		@SuppressWarnings("unchecked")
		List<College> collegeList = (List<College>)query.getResultList();
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			collegeListJson = objectMapper.writeValueAsString(collegeList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		em.getTransaction().commit();
		em.close();
		return collegeListJson;
	}
}
