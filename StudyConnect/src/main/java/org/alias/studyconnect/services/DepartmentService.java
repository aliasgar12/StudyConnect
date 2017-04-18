package org.alias.studyconnect.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.alias.studyconnect.model.Department;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DepartmentService {
	
	private ObjectMapper objectMapper;
	private EntityManager em;
	
	public String getDeptList() {
		
		String deptListJson="";
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("from Department");
		@SuppressWarnings("unchecked")
		List<Department> deptList = (List<Department>)query.getResultList();
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			deptListJson = objectMapper.writeValueAsString(deptList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		em.getTransaction().commit();
		em.close();
		return deptListJson;
	}

}
