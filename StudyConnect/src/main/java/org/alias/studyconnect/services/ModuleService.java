package org.alias.studyconnect.services;

import java.util.Set;

import javax.persistence.EntityManager;

import org.alias.studyconnect.model.Module;
import org.alias.studyconnect.model.Subject;
import org.alias.studyconnect.model.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ModuleService {
	
	private ObjectMapper objectMapper;
	private EntityManager em;
	
	public String moduleBySubject(int id) throws JsonProcessingException{
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		Subject subject = em.find(Subject.class, id); // will throw exception if subject not found
		Set<Module> modules = subject.getModules();
		objectMapper= new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String result = objectMapper.writeValueAsString(modules);
		em.getTransaction().commit();
		em.close();
		return result;
	}

	public String getStudentByModule(int moduleId) throws JsonProcessingException {
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		Module module = em.find(Module.class, moduleId);
		Set<UserDetails> users = module.getUser();
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String result = objectMapper.writeValueAsString(users);
		em.getTransaction().commit();
		em.close();
		return result;
	}

	public int moduleCompleted(int userId, Module mod) {
		int result;
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		Module module = em.find(Module.class, mod.getModuleId());
		UserDetails user = em.find(UserDetails.class, userId);
		try{
			module.getUser().add(user);
			user.getModuleCompleted().add(module);
			result = 1;
		}catch(NullPointerException e){
			e.printStackTrace();
			result =  0;
		}
		em.getTransaction().commit();
		em.close();
		
		return result;
	}
	
	
	
	
	
	
}
