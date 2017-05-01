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

	public String moduleBySubject(int id) throws JsonProcessingException {
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		Subject subject = em.find(Subject.class, id); // will throw exception if
														// subject not found
		Set<Module> modules = subject.getModules();
		objectMapper = new ObjectMapper();
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

	public String moduleCompleted(int userId, Module mod) {
		String result ="";
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		Module module = em.find(Module.class, mod.getModuleId());
		UserDetails user = em.find(UserDetails.class, userId);
		try {
			// check if the module is already completed 
			if (!module.getUser().contains(user))
				result = markCompleted(module, user);
			else
				result = markIncomplete(module, user);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return result;
		}
		em.getTransaction().commit();
		em.close();

		return result;
	}

	private String markIncomplete(Module module, UserDetails user) throws NullPointerException {
		module.getUser().remove(user);
		user.getModuleCompleted().remove(module);
		return "incomplete";
	}

	private String markCompleted(Module module, UserDetails user) throws NullPointerException {
		module.getUser().add(user);
		user.getModuleCompleted().add(module);
		return "complete";

	}

}
